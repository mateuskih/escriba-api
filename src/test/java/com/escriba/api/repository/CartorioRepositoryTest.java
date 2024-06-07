package com.escriba.api.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escriba.api.dto.CartorioAtribuicoesDTO;
import com.escriba.api.model.Cartorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CartorioRepositoryTest {

    @Mock
    private CartorioRepository cartorioRepository;

    @Test
    public void testFindCartoriosWithAtribuicoes() {
        List<CartorioAtribuicoesDTO> cartorios = Collections.emptyList();
        when(cartorioRepository.findCartoriosWithAtribuicoes()).thenReturn(cartorios);

        assertEquals(cartorios, cartorioRepository.findCartoriosWithAtribuicoes());
    }

    @Test
    public void testFindAllBy() {
        Pageable pageable = mock(Pageable.class);
        Page<Cartorio> page = mock(Page.class);
        when(cartorioRepository.findAllBy(pageable)).thenReturn(page);

        assertEquals(page, cartorioRepository.findAllBy(pageable));
    }

    @Test
    public void testGetReferenceById_Integer() {
        Integer id = 1;
        Cartorio cartorio = new Cartorio();
        when(cartorioRepository.getReferenceById(id)).thenReturn(cartorio);

        assertEquals(cartorio, cartorioRepository.getReferenceById(id));
    }

    @Test
    public void testGetReferenceById_String() {
        String id = "1";
        Cartorio cartorio = new Cartorio();
        when(cartorioRepository.getReferenceById(id)).thenReturn(cartorio);

        assertEquals(cartorio, cartorioRepository.getReferenceById(id));
    }

    @Test
    public void testExistsByNome() {
        String nome = "nome";
        when(cartorioRepository.existsByNome(nome)).thenReturn(true);

        assertTrue(cartorioRepository.existsByNome(nome));
    }

    @Test
    public void testExistsById() {
        String id = "1";
        when(cartorioRepository.existsById(id)).thenReturn(true);

        assertTrue(cartorioRepository.existsById(id));
    }

    @Test
    public void testFindByNome() {
        String nome = "nome";
        Cartorio cartorio = new Cartorio();
        cartorio.setNome(nome);
        when(cartorioRepository.findByNome(nome)).thenReturn(Optional.of(cartorio));

        assertEquals(nome, cartorioRepository.findByNome(nome).get().getNome());
    }
}
