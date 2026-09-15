package com.projeto.ecommerceapii.controller;

import com.projeto.ecommerceapii.dto.categoria.CreateCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.ResponseCategoriaDTO;
import com.projeto.ecommerceapii.service.CategoriaService;
import org.apache.coyote.BadRequestException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<ResponseCategoriaDTO> create(@RequestBody CreateCategoriaDTO dto) throws BadRequestException {
        return ResponseEntity.ok(categoriaService.create(dto));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws BadRequestException {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
