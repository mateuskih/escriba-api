package com.escriba.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.escriba.api.dto.AtribuicaoDTO;
import com.escriba.api.dto.AtribuicaoPaginationDTO;
import com.escriba.api.exception.DatabaseException;
import com.escriba.api.exception.ResourceNotFoundException;
import com.escriba.api.model.Atribuicao;
import com.escriba.api.repository.AtribuicaoRepository;

@Service
public class AtribuicaoService {

    @Autowired
    private AtribuicaoRepository repository;

    @Transactional(readOnly = true)
    public Page<AtribuicaoPaginationDTO> findAllByPagination(Pageable pageable) {
        Page<Atribuicao> result = repository.findAllBy(pageable);
        return result.map(AtribuicaoPaginationDTO::new);
    }

    @Transactional(readOnly = true)
    public AtribuicaoDTO findById(String id) {
        Atribuicao entity = findEntityById(id);
        return new AtribuicaoDTO(entity);
    }

    @Transactional
    public AtribuicaoDTO insert(AtribuicaoDTO dto) {
        verifyDuplicateRecordById(dto.getId());
        verifyDuplicateRecordByName(dto.getNome());

        Atribuicao entity = copyDtoToEntity(dto);
        Atribuicao savedEntity = repository.save(entity);
        return new AtribuicaoDTO(savedEntity);
    }

    @Transactional
    public AtribuicaoDTO update(String id, AtribuicaoDTO dto) {
        Atribuicao entity = findEntityById(id);

        if (!entity.getNome().equals(dto.getNome())) {
            verifyDuplicateRecordByName(dto.getNome());
        }

        entity.setNome(dto.getNome());
        entity.setSituacao(dto.isSituacao());
        Atribuicao savedEntity = repository.save(entity);
        return new AtribuicaoDTO(savedEntity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(String id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Registro utilizado em outro cadastro");
        }
    }

    private Atribuicao findEntityById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro com ID " + id + " não encontrado"));
    }

    private void verifyDuplicateRecordById(String id) {
        if (repository.findById(id).isPresent()) {
            throw new DatabaseException("Registro já cadastrado");
        }
    }

    private void verifyDuplicateRecordByName(String nome) {
        if (repository.findByNome(nome).isPresent()) {
            throw new DatabaseException("Nome já informado no registro com código: " +
                    repository.findByNome(nome).get().getId());
        }
    }

    private Atribuicao copyDtoToEntity(AtribuicaoDTO dto) {
        Atribuicao entity = new Atribuicao();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setSituacao(dto.isSituacao());
        return entity;
    }
}

