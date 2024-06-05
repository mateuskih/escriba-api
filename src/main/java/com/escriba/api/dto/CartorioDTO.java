package com.escriba.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.escriba.api.model.Cartorio;

import lombok.Data;

@Data
public class CartorioDTO {
    private Integer id;
    private String nome;
    private String observacao;
    private SituacaoDTO situacao;
    private List<AtribuicaoDTO> atribuicoes;

    public CartorioDTO(Cartorio entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.observacao = entity.getObservacao();
        this.situacao = new SituacaoDTO(entity.getSituacao());
        this.atribuicoes = entity.getAtribuicoes().stream()
                .map(AtribuicaoDTO::new)
                .collect(Collectors.toList());
    }
}
