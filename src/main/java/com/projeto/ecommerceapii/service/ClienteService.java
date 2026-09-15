package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.dto.cliente.CreateClienteDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseCLienteDTO;
import com.projeto.ecommerceapii.dto.ResponseItemPedidoDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseUpdateCliente;
import com.projeto.ecommerceapii.dto.pedido.ResponsePedidoDTO;
import com.projeto.ecommerceapii.dto.cliente.ResponseCreateClienteDTO;
import com.projeto.ecommerceapii.entity.Cliente;
import com.projeto.ecommerceapii.entity.ItemPedido;
import com.projeto.ecommerceapii.entity.Pedido;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.repository.ClienteRepository;
import com.projeto.ecommerceapii.repository.PedidoRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public ResponseCLienteDTO findById(Long id){

        Cliente cliente = repository.findById(id).orElseThrow( () -> new EntityNotFoundException("Cliente não encotrado"));
        List<ResponsePedidoDTO> pedidos = new ArrayList<>();

        for(Pedido pe : cliente.getPedidos()){
            List<ResponseItemPedidoDTO> itens = new ArrayList<>();

            for(ItemPedido item : pe.getItens()){
                ResponseItemPedidoDTO dto = new ResponseItemPedidoDTO(
                        item.getId(),item.getProduto().getNome(),item.getQuantidade(),item.getPrecoUnitario()
                );
                itens.add(dto);
            }

            ResponsePedidoDTO p1 = new ResponsePedidoDTO(pe.getId(), pe.getData(),pe.getStatus(), pe.getTotal(), itens);

            pedidos.add(p1);

        }

        return  new ResponseCLienteDTO(
                cliente.getId(), cliente.getNome(), cliente.getEmai(), cliente.getCpf(), pedidos
        );
    }

    public ResponseCreateClienteDTO create(CreateClienteDTO dto) throws BadRequestException {

        Cliente cliente = clienteCreated(dto);
        repository.save(cliente);

        return new ResponseCreateClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmai(), cliente.getCpf());
    }

    private Cliente clienteCreated(CreateClienteDTO dto) throws BadRequestException {
        if(repository.findByCpf(dto.cpf()).isPresent()){
            throw new BadRequestException("Cpf já cadastrado");
        }
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmai(dto.email());

        return  cliente;
    }


    public void delete(Long id) throws BadRequestException {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encotrado"));
        boolean pedidoCliente = pedidoRepository.existsByClienteId(cliente.getId());
        if(pedidoCliente){
            throw new BadRequestException("Cliente em pedido, não pode ser excluido");
        }
        repository.delete(cliente);
    }

    public ResponseUpdateCliente update(Long id, CreateClienteDTO dto){

        Cliente entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encotrado"));

        entity.setNome(dto.nome());
        entity.setEmai(dto.email());
        entity.setCpf(dto.cpf());

        repository.save(entity);

        return new ResponseUpdateCliente(
                entity.getNome(),
                entity.getEmai(),
                entity.getCpf()
        );
    }


}
