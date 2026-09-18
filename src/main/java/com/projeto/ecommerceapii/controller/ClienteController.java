package com.projeto.ecommerceapii.controller;

import com.projeto.ecommerceapii.dto.cliente.CreateClienteDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseCLienteDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseCreateClienteDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseUpdateCliente;
import com.projeto.ecommerceapii.service.ClienteService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseCLienteDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseCLienteDTO>> findAll(Integer pagina, Integer itens){
        return ResponseEntity.ok(service.findAll(pagina,itens));
    }

    @PostMapping
    public ResponseEntity<ResponseCLienteDTO> create(@RequestBody CreateClienteDTO dto) throws BadRequestException {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseCLienteDTO> update(@PathVariable Long id, @RequestBody CreateClienteDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws BadRequestException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
