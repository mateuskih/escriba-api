package com.escriba.api.dto;

import com.escriba.api.model.Situacao;

import lombok.Data;

@Data
public class SituacaoDTO {
    private String id;
    private String nome;

    public SituacaoDTO(Situacao entity) {
        id = entity.getId();
        nome = entity.getNome();
    }
}