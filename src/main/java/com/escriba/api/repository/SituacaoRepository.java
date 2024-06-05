package com.escriba.api.repository;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.escriba.api.model.Situacao;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, String> {
    
    Page<Situacao> findAllBy(Pageable pageable);

    Situacao getReferenceById(String id);

    boolean existsByNome(String nome);

    boolean existsById(@NonNull String id);

    @Query("SELECT s FROM Situacao s WHERE s.nome = :nome")
    Optional<Situacao> findByNome(@Param("nome") String nome);
}
