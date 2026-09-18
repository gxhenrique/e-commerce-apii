package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.config.JWTUserData;
import com.projeto.ecommerceapii.dto.itemPedido.CreateItemPedidoDTO;
import com.projeto.ecommerceapii.dto.pedido.CreatePedidoDTO;
import com.projeto.ecommerceapii.dto.pedido.ResponsePedidoDTO;
import com.projeto.ecommerceapii.entity.*;
import com.projeto.ecommerceapii.exception.BadRequestException;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.mapper.pedido.PedidoMapper;
import com.projeto.ecommerceapii.repository.ClienteRepository;
import com.projeto.ecommerceapii.repository.ItemPedidoRepository;
import com.projeto.ecommerceapii.repository.PedidoRepository;
import com.projeto.ecommerceapii.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoMapper pedidoMapper;


    public PedidoService(
            PedidoRepository pedidoRepository,
            ItemPedidoRepository itemPedidoRepository,
            ProdutoRepository produtoRepository,
            ClienteRepository clienteRepository,
            PedidoMapper pedidoMapper){

        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoMapper = pedidoMapper;


    }

    public Page<ResponsePedidoDTO> findAll(Integer paginas, Integer itens){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JWTUserData userData =
                (JWTUserData) authentication.getPrincipal();

        Page<Pedido> pedidos;

        System.out.println("USER ID: " + userData.userId());
        System.out.println("CLIENTE ID: " + userData.clienteId());
        System.out.println("EMAIL: " + userData.email());
        System.out.println("ROLE: " + userData.role());

        if(userData.role().equals("ADMIN")){
            pedidos = pedidoRepository.findAll(PageRequest.of(paginas,itens));

            return pedidos.map(pedidoMapper::toResponse);
        }

       pedidos = pedidoRepository.findAllByClienteId(
                userData.clienteId(),
                PageRequest.of(paginas, itens)
        );


        return pedidos.map(pedidoMapper::toResponse);
    }

    public ResponsePedidoDTO findById(Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JWTUserData userData =
                (JWTUserData) authentication.getPrincipal();

        //System.out.println("Authentication: " + authentication);
        System.out.println("UserData : " + userData);
        Pedido pedido;

        if(userData.role().equals("ADMIN")){
            pedido = pedidoRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException("Pedido não encotrado"));
        }else {
            pedido = pedidoRepository.findByIdAndClienteId(id, userData.clienteId())
                    .orElseThrow(() -> new EntityNotFoundException("pedido do cliente não encotrado..."));
        }

        return pedidoMapper.toResponse(pedido);
    }

    @Transactional
    public ResponsePedidoDTO create(CreatePedidoDTO pedidoDTO) throws BadRequestException {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        Long clienteId = userData.clienteId();



        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(Status.PENDENTE);

        pedidoRepository.save(pedido);

        for(CreateItemPedidoDTO dto : pedidoDTO.itens()){

            ItemPedido itemPedido = new ItemPedido();

            Produto produto = produtoRepository.findById(dto.produtoId())
                    .orElseThrow( () -> new EntityNotFoundException("Produto não cadastrado"));

            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(dto.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());
            itemPedido.setPedido(pedido);

            pedido.getItens().add(itemPedido);

            // Teste de diminuir o estoque
            if(pedido.getStatus().equals(Status.PAGO)){
                produto.diminuirEstoque(dto.quantidade());
                produtoRepository.save(produto);
            }

            itemPedidoRepository.save(itemPedido);


        }

        return pedidoMapper.toResponse(pedido);
    }


}
