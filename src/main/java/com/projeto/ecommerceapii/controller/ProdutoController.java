package com.projeto.ecommerceapii.controller;

import com.projeto.ecommerceapii.dto.produto.CreateProdutoDTO;
import com.projeto.ecommerceapii.dto.produto.ResponseProdutoDTO;
import com.projeto.ecommerceapii.dto.produto.UpdateProdutoDTO;
import com.projeto.ecommerceapii.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseProdutoDTO> findById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseProdutoDTO>> findALL(@Valid @RequestParam Integer pagina,
                                                            @RequestParam Integer itens){
        return ResponseEntity.ok(produtoService.findAll(pagina,itens));
    }

    @PostMapping
    public ResponseEntity<ResponseProdutoDTO> create(@Valid @RequestBody CreateProdutoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.create(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseProdutoDTO> udate(@Valid @PathVariable Long id,@RequestBody UpdateProdutoDTO dto){
        return ResponseEntity.ok(produtoService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseProdutoDTO> delete(@Valid @PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
