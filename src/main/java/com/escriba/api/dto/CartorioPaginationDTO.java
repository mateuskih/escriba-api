package com.escriba.api.dto;

import com.escriba.api.model.Cartorio;

import lombok.Data;

@Data
public class CartorioPaginationDTO {
    private int id;
    private String nome;

    public CartorioPaginationDTO(Cartorio entity) {
        id = entity.getId();
        nome = entity.getNome();
    }
}
