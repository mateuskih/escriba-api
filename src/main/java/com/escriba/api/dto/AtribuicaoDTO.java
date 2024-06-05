package com.escriba.api.dto;

import com.escriba.api.model.Atribuicao;

import lombok.Data;

@Data
public class AtribuicaoDTO {
    private String id;
    private String nome;
    private boolean situacao = true;

    public AtribuicaoDTO(Atribuicao entity) {
        id = entity.getId();
        nome = entity.getNome();
        situacao = entity.isSituacao();
    }
}
