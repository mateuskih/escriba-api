package com.escriba.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.escriba.api.dto.AtribuicaoDTO;
import com.escriba.api.dto.CartorioDTO;
import com.escriba.api.dto.CartorioPaginationDTO;
import com.escriba.api.dto.SituacaoDTO;
import com.escriba.api.exception.DatabaseException;
import com.escriba.api.exception.ResourceNotFoundException;
import com.escriba.api.model.Atribuicao;
import com.escriba.api.model.Cartorio;
import com.escriba.api.model.Situacao;
import com.escriba.api.repository.AtribuicaoRepository;
import com.escriba.api.repository.CartorioRepository;
import com.escriba.api.repository.SituacaoRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartorioService {

    @Autowired
    private CartorioRepository cartorioRepository;
    
    @Autowired
    private AtribuicaoRepository atribuicaoRepository;
    
    @Autowired
    private SituacaoRepository situacaoRepository;

    @Transactional(readOnly = true)
    public Page<CartorioPaginationDTO> findAllBy(Pageable pageable) {
        return cartorioRepository.findAll(pageable).map(CartorioPaginationDTO::new);
    }

    @Transactional(readOnly = true)
    public Cartorio findById(Integer id) {
        return cartorioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Transactional
    public CartorioDTO insert(CartorioDTO dto) {
        verifyDuplicateName(dto.getNome(), null);
        Cartorio entity = new Cartorio();
        copyDtoToEntity(dto, entity);
        entity = cartorioRepository.save(entity);
        return new CartorioDTO(entity);
    }

    @Transactional
    public CartorioDTO update(Integer id, CartorioDTO dto) {
        Cartorio existing = findById(id);
        verifyDuplicateName(dto.getNome(), existing.getId());
        copyDtoToEntity(dto, existing);
        return new CartorioDTO(cartorioRepository.save(existing));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Integer id) {
        try {
            cartorioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Registro utilizado em outro cadastro");
        }
    }

    private void copyDtoToEntity(CartorioDTO dto, Cartorio entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setObservacao(dto.getObservacao());
        entity.setSituacao(resolveSituacao(dto.getSituacao()));
        entity.setAtribuicoes(resolveAtribuicoes(dto.getAtribuicoes()));
    }

    private Situacao resolveSituacao(SituacaoDTO situacaoDTO) {
        return situacaoDTO != null ? situacaoRepository.getReferenceById(situacaoDTO.getId()) :
                                      situacaoRepository.findByNome("SIT_BLOQUEADO").orElse(null);
    }

    private List<Atribuicao> resolveAtribuicoes(List<AtribuicaoDTO> atribuicoesDTO) {
    	return atribuicoesDTO != null ? atribuicoesDTO.stream()
                .map(atribuicaoDTO -> atribuicaoRepository.getReferenceById(atribuicaoDTO.getId()))
                .collect(Collectors.toList()) :
                new ArrayList<>();
    }

    private void verifyDuplicateName(String nome, Integer id) {
        Optional<Cartorio> existingByName = cartorioRepository.findByNome(nome);
        existingByName.ifPresent(cartorio -> {
            if (!Objects.equals(cartorio.getId(), id)) {
                throw new DatabaseException("Nome já informado no registro com código: " + cartorio.getId());
            }
        });
    }
}

