package com.projeto.ecommerceapii.mapper.cliente;

import com.projeto.ecommerceapii.dto.cliente.CreateClienteDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseCLienteDTO;
import com.projeto.ecommerceapii.dto.pedido.ResponsePedidoDTO;
import com.projeto.ecommerceapii.entity.Cliente;
import com.projeto.ecommerceapii.mapper.pedido.PedidoMapper;
import com.projeto.ecommerceapii.repository.ClienteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    private final PedidoMapper pedidoMapper;
    private final ClienteRepository clienteRepository;

    public ClienteMapper(PedidoMapper pedidoMapper, ClienteRepository clienteRepository) {
        this.pedidoMapper = pedidoMapper;
        this.clienteRepository = clienteRepository;
    }

    public ResponseCLienteDTO toResponse(Cliente cliente){

        List<ResponsePedidoDTO> pedidos = cliente.getPedidos().stream().map(
                pedidoMapper::toResponse

        ).toList();

        return new ResponseCLienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmai(),
                cliente.getCpf(),
                pedidos);
    }

    public Cliente toEntity(CreateClienteDTO dto) throws BadRequestException {

        if(clienteRepository.findByCpf(dto.cpf()).isPresent()){
            throw new BadRequestException("Cpf já cadastrado");
        }
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmai(dto.email());

        return  cliente;
    }

}
