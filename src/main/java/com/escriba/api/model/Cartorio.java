package com.escriba.api.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name = "cartorios")
@AllArgsConstructor
@NoArgsConstructor
public class Cartorio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(max = 150, message = "Nome deve ter no máximo 50 caracteres")
    @NotBlank(message = "Nome é obrigatório")
    @Column(unique = true)
    private String nome;

    @Column(length = 250)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    private Situacao situacao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cartorio_atribuicao",
            joinColumns = @JoinColumn(name = "cartorio_id"),
            inverseJoinColumns = @JoinColumn(name = "atribuicao_id")
    )
    @JsonManagedReference
    private List<Atribuicao> atribuicoes;
	
}
