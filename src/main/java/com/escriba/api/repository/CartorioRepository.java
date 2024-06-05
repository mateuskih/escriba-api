package com.escriba.api.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.escriba.api.dto.CartorioAtribuicoesDTO;
import com.escriba.api.model.Cartorio;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Integer> {

    @Query(value = "SELECT c.id AS cartorio_id, c.nome AS cartorio_nome, c.observacao AS cartorio_observacao, " +
                   "s.id AS situacao_id, s.nome AS situacao_nome, GROUP_CONCAT(a.nome) AS atribuicoes " +
                   "FROM cartorios c " +
                   "JOIN situacoes s ON c.situacao_id = s.id " +
                   "JOIN cartorio_atribuicao ca ON c.id = ca.cartorio_id " +
                   "JOIN atribuicoes a ON ca.atribuicao_id = a.id " +
                   "GROUP BY c.id, c.nome, c.observacao, s.id, s.nome", 
                   nativeQuery = true)
    List<CartorioAtribuicoesDTO> findCartoriosWithAtribuicoes();

    Page<Cartorio> findAllBy(Pageable pageable);

    Cartorio getReferenceById(Integer id);

    Cartorio getReferenceById(String id);

    boolean existsByNome(String nome);

    boolean existsById(String id);

    Optional<Cartorio> findByNome(String nome);
}
