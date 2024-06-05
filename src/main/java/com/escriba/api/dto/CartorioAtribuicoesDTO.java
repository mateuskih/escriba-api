package com.escriba.api.dto;
import lombok.Data;

@Data
public class CartorioAtribuicoesDTO {
    private Integer cartorioId;
    private String cartorioNome;
    private String cartorioObservacao;
    private Integer situacaoId;
    private String situacaoNome;
    private String atribuicoes;
}

