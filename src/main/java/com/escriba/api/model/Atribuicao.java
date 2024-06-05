package com.escriba.api.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name = "atribuicoes")
@AllArgsConstructor
@NoArgsConstructor
public class Atribuicao {
	@Size(max = 20, message = "ID deve ter no máximo 20 caracteres")
    @NotBlank(message = "ID é obrigatório")
    @Id
    private String id;

    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    @NotBlank(message = "Nome é obrigatório")
    @Column(unique = true)
    private String nome;

    @Column(nullable = false)
    private boolean situacao = true;

    @ManyToMany(mappedBy = "atribuicoes")
    @JsonBackReference
    private List<Cartorio> cartorios;
    
}
