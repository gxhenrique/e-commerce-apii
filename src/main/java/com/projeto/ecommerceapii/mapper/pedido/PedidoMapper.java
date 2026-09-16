package com.projeto.ecommerceapii.mapper.pedido;

import com.projeto.ecommerceapii.dto.ResponseItemPedidoDTO;
import com.projeto.ecommerceapii.dto.pedido.ResponsePedidoDTO;
import com.projeto.ecommerceapii.entity.Pedido;
import com.projeto.ecommerceapii.mapper.itemPedido.ItemPedidoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    private final ItemPedidoMapper itemPedidoMapper;

    public PedidoMapper(ItemPedidoMapper itemPedidoMapper) {
        this.itemPedidoMapper = itemPedidoMapper;
    }

    public ResponsePedidoDTO toResponse(Pedido pedido){

        List<ResponseItemPedidoDTO> itens = pedido.getItens().stream().map(
                itemPedidoMapper::toResponse).toList();


        return new ResponsePedidoDTO(pedido.getId(), pedido.getData(),pedido.getStatus(),pedido.getTotal(), itens);
    }


}
