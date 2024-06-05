package com.escriba.api.dto;

import com.escriba.api.model.Situacao;

import lombok.Data;

@Data
public class SituacaoPaginationDTO {
    private String id;
    private String nome;

    public SituacaoPaginationDTO(Situacao entity) {
        id = entity.getId();
        nome = entity.getNome();
    }
}
