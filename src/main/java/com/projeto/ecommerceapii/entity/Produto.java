package com.projeto.ecommerceapii.entity;

import com.projeto.ecommerceapii.exception.BadRequestException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    private String descricao;

    @Positive(message = "preço deve ser positivo...")
    private BigDecimal preco;
    @Min(value = 0, message = "Estoque deve ser maior ou igual a 0...")
    private Integer estoque;
    @NotNull
    private Boolean ativo;

    @ManyToOne
    private Categoria categoria;


    public Produto(){

    }

    public Produto(Long id, String nome, String descricao, BigDecimal preco, Integer estoque, Boolean ativo, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.ativo = ativo;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void diminuirEstoque(Integer quantidade) throws BadRequestException {
        if(quantidade > estoque){
            throw new BadRequestException("Estoque insuficiente");
        }

        this.estoque -= quantidade;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                ", ativo=" + ativo +
                ", categoria=" + categoria +
                '}';
    }
}