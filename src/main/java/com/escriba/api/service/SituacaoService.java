package com.escriba.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.escriba.api.dto.SituacaoDTO;
import com.escriba.api.dto.SituacaoPaginationDTO;
import com.escriba.api.exception.DatabaseException;
import com.escriba.api.exception.ResourceNotFoundException;
import com.escriba.api.model.Situacao;
import com.escriba.api.repository.SituacaoRepository;

@Service
public class SituacaoService {

    @Autowired
    private SituacaoRepository repository;

    @Transactional(readOnly = true)
    public Page<SituacaoPaginationDTO> findAllBy(Pageable pageable) {
        Page<Situacao> result = repository.findAllBy(pageable);
        return result.map(SituacaoPaginationDTO::new);
    }

    @Transactional(readOnly = true)
    public SituacaoDTO findById(String id) {
        Situacao entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new SituacaoDTO(entity);
    }

    @Transactional
    public SituacaoDTO insert(SituacaoDTO dto) {
        verifyIfRecordExistsById(dto.getId());
        verifyIfRecordExistsByName(dto.getNome());

        Situacao entity = new Situacao();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new SituacaoDTO(entity);
    }

    @Transactional
    public SituacaoDTO update(String id, SituacaoDTO dto) {
        Situacao entity = repository.findById(id)
                .orElseThrow(() -> new DatabaseException("Registro com ID " + id + " não encontrado"));

        if (!entity.getNome().equals(dto.getNome())) {
            verifyIfRecordExistsByName(dto.getNome());
        }

        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new SituacaoDTO(entity);
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

    private void copyDtoToEntity(SituacaoDTO dto, Situacao entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
    }

    private void verifyIfRecordExistsById(String id) {
        if (repository.findById(id).isPresent()) {
            throw new DatabaseException("Registro já cadastrado");
        }
    }

    private void verifyIfRecordExistsByName(String nome) {
        if (repository.findByNome(nome).isPresent()) {
            throw new DatabaseException("Nome já informado no registro com código: " + repository.findByNome(nome).get().getId());
        }
    }
}

