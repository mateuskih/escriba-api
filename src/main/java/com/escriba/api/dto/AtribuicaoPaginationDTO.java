package com.escriba.api.dto;

import com.escriba.api.model.Atribuicao;

import lombok.Data;

@Data
public class AtribuicaoPaginationDTO {
    private String id;
    private String nome;

    public AtribuicaoPaginationDTO(Atribuicao entity) {
        id = entity.getId();
        nome = entity.getNome();
    }
}