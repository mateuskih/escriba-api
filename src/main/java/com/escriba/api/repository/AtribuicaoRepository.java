package com.escriba.api.repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.escriba.api.model.Atribuicao;

@Repository
public interface AtribuicaoRepository extends JpaRepository<Atribuicao, String> {
    
    Page<Atribuicao> findAllBy(Pageable pageable);

    Atribuicao getReferenceById(String id);

    boolean existsByNome(String nome);

    boolean existsById(@NonNull String id);

    Optional<Atribuicao> findByNome(String nome);
}
