package com.projeto.ecommerceapii.controller;

import com.projeto.ecommerceapii.dto.categoria.CreateCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.ResponseCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.UpdateCategoriaDTO;
import com.projeto.ecommerceapii.service.CategoriaService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseCategoriaDTO> findById( @Valid @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseCategoriaDTO>> findAll(@Valid @RequestParam Integer pagina, @RequestParam Integer itens) {
        return ResponseEntity.ok(categoriaService.finAll(pagina,itens));
    }


    @PostMapping
    public ResponseEntity<ResponseCategoriaDTO> create( @Valid @RequestBody CreateCategoriaDTO dto) throws BadRequestException {
        return ResponseEntity.ok(categoriaService.create(dto));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws BadRequestException {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseCategoriaDTO> create(@Valid @PathVariable Long id, @RequestBody UpdateCategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.update(id, dto));
    }
}
