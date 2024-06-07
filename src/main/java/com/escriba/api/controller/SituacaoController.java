package com.escriba.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escriba.api.dto.SituacaoDTO;
import com.escriba.api.dto.SituacaoPaginationDTO;
import com.escriba.api.exception.ResourceNotFoundException;
import com.escriba.api.service.SituacaoService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/situacoes")
public class SituacaoController {

    private final SituacaoService service;

    public SituacaoController(SituacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<SituacaoPaginationDTO>> findAll(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<SituacaoPaginationDTO> dto = service.findAllBy(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoDTO> findById(@PathVariable String id) {
        try {
        	SituacaoDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<SituacaoDTO> insert(@Valid @RequestBody SituacaoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SituacaoDTO> update(@PathVariable String id, @Valid @RequestBody SituacaoDTO dto) {
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
