package com.escriba.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escriba.api.dto.CartorioDTO;
import com.escriba.api.dto.CartorioPaginationDTO;
import com.escriba.api.exception.ResourceNotFoundException;
import com.escriba.api.model.Cartorio;
import com.escriba.api.service.CartorioService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartorios")
public class CartorioController {

    private final CartorioService service;

    public CartorioController(CartorioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CartorioPaginationDTO>> findAll(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<CartorioPaginationDTO> dto = service.findAllBy(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cartorio> getCartorioById(@PathVariable Integer id) {
        try {
        	Cartorio cartorio = service.findById(id);
            return ResponseEntity.ok(cartorio);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<CartorioDTO> insert(@Valid @RequestBody CartorioDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartorioDTO> update(@PathVariable Integer id, @Valid @RequestBody CartorioDTO dto) {
        try {
            dto = service.update(id, dto);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
