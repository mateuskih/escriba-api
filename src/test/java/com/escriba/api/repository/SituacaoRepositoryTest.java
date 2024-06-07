package com.escriba.api.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.escriba.api.model.Situacao;

@SpringBootTest
public class SituacaoRepositoryTest {

    @Mock
    private SituacaoRepository situacaoRepository;

    @Test
    public void testFindAllBy() {
        Pageable pageable = mock(Pageable.class);
        Page<Situacao> page = mock(Page.class);
        when(situacaoRepository.findAllBy(pageable)).thenReturn(page);

        assertEquals(page, situacaoRepository.findAllBy(pageable));
    }

    @Test
    public void testExistsByNome() {
        String nome = "nome";
        when(situacaoRepository.existsByNome(nome)).thenReturn(true);

        assertTrue(situacaoRepository.existsByNome(nome));
    }

    @Test
    public void testExistsById() {
        String id = "id";
        when(situacaoRepository.existsById(id)).thenReturn(true);

        assertTrue(situacaoRepository.existsById(id));
    }

    @Test
    public void testFindByNome() {
        String nome = "nome";
        Situacao situacao = new Situacao();
        situacao.setNome(nome);
        when(situacaoRepository.findByNome(nome)).thenReturn(Optional.of(situacao));

        assertEquals(nome, situacaoRepository.findByNome(nome).get().getNome());
    }
}
