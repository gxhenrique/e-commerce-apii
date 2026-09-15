package com.projeto.ecommerceapii.controller;

import com.projeto.ecommerceapii.dto.pedido.CreatePedidoDTO;
import com.projeto.ecommerceapii.dto.pedido.ResponsePedidoDTO;
import com.projeto.ecommerceapii.service.PedidoService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}" )
    public ResponseEntity<ResponsePedidoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ResponsePedidoDTO>> findAll(@RequestParam Integer pagina,
                                           @RequestParam Integer itens){
        return ResponseEntity.ok(service.findAll(pagina,itens));
    }

    @PostMapping
    public ResponseEntity<ResponsePedidoDTO> create(@Valid @RequestBody CreatePedidoDTO dto) throws BadRequestException {
        return ResponseEntity.ok(service.create(dto));
    }
}
