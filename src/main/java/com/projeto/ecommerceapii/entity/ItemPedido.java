package com.projeto.ecommerceapii.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1L, message = "Valor minimo é 1")
    private Integer quantidade;
    @Positive(message = "Valor invalido")
    private BigDecimal precoUnitario;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

    public ItemPedido(){

    }


    public ItemPedido(Long id, Pedido pedido, Produto produto, BigDecimal precoUnitario, Integer quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }


    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getSubTotal(){
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
