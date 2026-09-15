package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.dto.ResponseItemPedidoDTO;
import com.projeto.ecommerceapii.dto.itemPedido.CreateItemPedidoDTO;
import com.projeto.ecommerceapii.dto.pedido.CreatePedidoDTO;
import com.projeto.ecommerceapii.dto.pedido.ResponsePedidoDTO;
import com.projeto.ecommerceapii.entity.*;
import com.projeto.ecommerceapii.exception.BadRequestException;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.repository.ClienteRepository;
import com.projeto.ecommerceapii.repository.ItemPedidoRepository;
import com.projeto.ecommerceapii.repository.PedidoRepository;
import com.projeto.ecommerceapii.repository.ProdutoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<ResponsePedidoDTO> findAll(Integer paginas, Integer itens){

        Page<Pedido> pedidos = pedidoRepository.findAll(PageRequest.of(paginas,itens));

        /*
        List<ResponseItemPedidoDTO> novosItens = pedidos.stream().flatMap(pedido -> pedido.getItens().stream()).map(
                itemPedido -> new ResponseItemPedidoDTO(
                        itemPedido.getId(),itemPedido.getProduto().getNome(),itemPedido.getQuantidade(),itemPedido.getPrecoUnitario()
                )
        ).toList();

         */

        Page<ResponsePedidoDTO> response = pedidos.map(pedido -> {

           List<ResponseItemPedidoDTO> itens2 = pedido.getItens().stream().map(
                   itemPedido -> new ResponseItemPedidoDTO(
                           itemPedido.getId(),itemPedido.getProduto().getNome(),
                           itemPedido.getQuantidade(),itemPedido.getPrecoUnitario())
           ).toList();

           return new ResponsePedidoDTO(
                   pedido.getId(),
                   pedido.getData(),
                   pedido.getStatus(),
                   pedido.getTotal(),
                   itens2
           );
        });




        /*
        Page<ResponsePedidoDTO> response = pedidos.map(pedido -> new ResponsePedidoDTO(
                pedido.getId(),pedido.getData(),pedido.getStatus(),pedido.getTotal(),novosItens
        ));

         */


        return  response;
    }

    public ResponsePedidoDTO findById(Long id){

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encotrado"));

        List<ResponseItemPedidoDTO> itens = pedido.getItens().stream()
                .map(itemPedido ->
                        new ResponseItemPedidoDTO(
                                itemPedido.getId(),itemPedido.getProduto().getNome(),
                                itemPedido.getQuantidade(), itemPedido.getPrecoUnitario())).toList();

        return new ResponsePedidoDTO(pedido.getId(),pedido.getData(),pedido.getStatus(),pedido.getTotal(),itens);
    }



    @Transactional
    public ResponsePedidoDTO create(CreatePedidoDTO pedidoDTO) throws BadRequestException {

        Cliente cliente = clienteRepository.findById(pedidoDTO.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(Status.PENDENTE);

        pedidoRepository.save(pedido);


        List<ResponseItemPedidoDTO> list = new ArrayList<>();

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


            ResponseItemPedidoDTO itemDTo = new ResponseItemPedidoDTO(
                    itemPedido.getId(),itemPedido.getProduto().getNome(),
                    itemPedido.getQuantidade(), itemPedido.getPrecoUnitario());

            list.add(itemDTo);

        }


        return new ResponsePedidoDTO(pedido.getId(),pedido.getData(),pedido.getStatus(),pedido.getTotal(),list);
    }


}
