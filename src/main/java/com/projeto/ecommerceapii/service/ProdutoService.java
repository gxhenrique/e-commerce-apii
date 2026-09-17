package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.dto.produto.CreateProdutoDTO;
import com.projeto.ecommerceapii.dto.produto.ResponseProdutoDTO;
import com.projeto.ecommerceapii.dto.produto.UpdateProdutoDTO;
import com.projeto.ecommerceapii.entity.Produto;
import com.projeto.ecommerceapii.exception.BadRequestException;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.mapper.produto.ProdutoMapper;
import com.projeto.ecommerceapii.repository.ItemPedidoRepository;
import com.projeto.ecommerceapii.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final ItemPedidoRepository itemPedidoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper, ItemPedidoRepository itemPedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
        this.itemPedidoRepository = itemPedidoRepository;
    }


    public Page<ResponseProdutoDTO> findAll(Integer pagina, Integer itens) {

        Page<Produto> produtos = produtoRepository.findAll(
                PageRequest.of(pagina, itens)
        );

        return produtos.map(produtoMapper::toResponse);
    }

    public ResponseProdutoDTO findById(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Produto não encontrado"));

        return produtoMapper.toResponse(produto);
    }

    public ResponseProdutoDTO create(CreateProdutoDTO dto) {

        Produto produto = produtoMapper.toEntity(dto);

        produtoRepository.save(produto);

        return produtoMapper.toResponse(produto);
    }

    public ResponseProdutoDTO update(Long id, UpdateProdutoDTO dto) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Produto não encontrado"));

        Produto novoProduto = produtoMapper.toEntityUpdate(produto,dto);
        produtoRepository.save(novoProduto);

        return produtoMapper.toResponse(produto);
    }

    public void delete(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Produto não encontrado"));

        if(itemPedidoRepository.existsByProdutoId(id)){
            throw new BadRequestException(("Produto vinculado com item Pedido..."));
        }

        produtoRepository.delete(produto);
    }
}
