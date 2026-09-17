package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.dto.cliente.CreateClienteDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseCLienteDTO;

import com.projeto.ecommerceapii.entity.Cliente;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.mapper.cliente.ClienteMapper;
import com.projeto.ecommerceapii.repository.ClienteRepository;
import com.projeto.ecommerceapii.repository.PedidoRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;



@Service
public class ClienteService {


    private final ClienteRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository repository,
                          PedidoRepository pedidoRepository,
                          ClienteMapper clienteMapper) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.clienteMapper = clienteMapper;
    }


    public ResponseCLienteDTO findById(Long id){

        Cliente cliente = repository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente não encotrado"));

        return clienteMapper.toResponse(cliente);
    }

    public ResponseCLienteDTO create(CreateClienteDTO dto) throws BadRequestException {

        Cliente cliente = clienteMapper.toEntity(dto);
        repository.save(cliente);

        return clienteMapper.toResponse(cliente);
    }


    public void delete(Long id) throws BadRequestException {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encotrado"));

        boolean pedidoCliente = pedidoRepository.existsByClienteId(cliente.getId());

        if(pedidoCliente){
            throw new BadRequestException("Cliente tem pedido, não pode ser excluido");
        }
        repository.delete(cliente);
    }

    public ResponseCLienteDTO update(Long id, CreateClienteDTO dto){

        Cliente entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encotrado"));

        entity.setNome(dto.nome());
        entity.setEmai(dto.email());
        entity.setCpf(dto.cpf());

        repository.save(entity);

        return clienteMapper.toResponse(entity);
    }


}
