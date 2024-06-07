package com.escriba.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escriba.api.dto.AtribuicaoDTO;
import com.escriba.api.dto.AtribuicaoPaginationDTO;
import com.escriba.api.exception.ResourceNotFoundException;
import com.escriba.api.service.AtribuicaoService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/atribuicoes")
public class AtribuicaoController {

    private final AtribuicaoService service;

    public AtribuicaoController(AtribuicaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<AtribuicaoPaginationDTO>> findAll(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<AtribuicaoPaginationDTO> dto = service.findAllByPagination(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtribuicaoDTO> findById(@PathVariable String id) {
        try {
        	AtribuicaoDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<AtribuicaoDTO> insert(@Valid @RequestBody AtribuicaoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtribuicaoDTO> update(@PathVariable String id, @Valid @RequestBody AtribuicaoDTO dto) {
        try {
            dto = service.update(id, dto);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
