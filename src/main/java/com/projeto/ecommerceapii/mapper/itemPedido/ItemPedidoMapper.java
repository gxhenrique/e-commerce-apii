package com.projeto.ecommerceapii.mapper.itemPedido;

import com.projeto.ecommerceapii.dto.ResponseItemPedidoDTO;
import com.projeto.ecommerceapii.entity.ItemPedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ResponseItemPedidoDTO toResponse(ItemPedido itemPedido){
        return new ResponseItemPedidoDTO(
                itemPedido.getId(),itemPedido.getProduto().getNome(),itemPedido.getQuantidade(), itemPedido.getPrecoUnitario());
    }
}
