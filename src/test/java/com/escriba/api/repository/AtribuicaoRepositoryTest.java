package com.escriba.api.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.escriba.api.model.Atribuicao;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AtribuicaoRepositoryTest {

    @Mock
    private AtribuicaoRepository atribuicaoRepository;

    @Test
    public void testFindAllBy() {
        Pageable pageable = mock(Pageable.class);
        Page<Atribuicao> page = new PageImpl<>(Collections.emptyList());
        when(atribuicaoRepository.findAllBy(pageable)).thenReturn(page);

        assertEquals(page, atribuicaoRepository.findAllBy(pageable));
    }

    @Test
    public void testExistsByNome() {
        String nome = "test";
        when(atribuicaoRepository.existsByNome(nome)).thenReturn(true);

        assertTrue(atribuicaoRepository.existsByNome(nome));
    }

    @Test
    public void testExistsById() {
        String id = "15";
        when(atribuicaoRepository.existsById(id)).thenReturn(true);

        assertTrue(atribuicaoRepository.existsById(id));
    }

    @Test
    public void testFindByNome() {
        String nome = "test";
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setNome(nome);
        when(atribuicaoRepository.findByNome(nome)).thenReturn(Optional.of(atribuicao));

        assertEquals(nome, atribuicaoRepository.findByNome(nome).get().getNome());
    }
}
