package com.projeto.ecommerceapii.mapper.produto;

import com.projeto.ecommerceapii.dto.produto.CreateProdutoDTO;
import com.projeto.ecommerceapii.dto.produto.ResponseProdutoDTO;
import com.projeto.ecommerceapii.dto.produto.UpdateProdutoDTO;
import com.projeto.ecommerceapii.entity.Categoria;
import com.projeto.ecommerceapii.entity.Produto;
import com.projeto.ecommerceapii.exception.BadRequestException;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.repository.CategoriaRepository;
import com.projeto.ecommerceapii.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoMapper(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    public ResponseProdutoDTO toResponse(Produto produto){

        return new ResponseProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getAtivo(),
                produto.getCategoria().getNome()
        );

    }

    public Produto toEntity(CreateProdutoDTO dto){

        Categoria categoria = categoriaRepository.findById(dto.categoriaId()).orElseThrow(
                () -> new EntityNotFoundException("Categoria não encotrada...")
        );


        if(produtoRepository.findByNome(dto.nome()).isPresent()){
            throw new BadRequestException("Produto já cadastrado...");
        }

        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());

        if(dto.ativo() == null){
            produto.setAtivo(false);
        }else {
            produto.setAtivo(dto.ativo());
        }

        produto.setCategoria(categoria);

        return  produto;
    }

    public Produto toEntityUpdate(Produto produto, UpdateProdutoDTO dto){

        Categoria categoria = categoriaRepository.findById(dto.categoriaId()).orElseThrow(
                () -> new EntityNotFoundException("Categoria não encotrada...")
        );


        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());

        if(dto.ativo() == null){
            produto.setAtivo(false);
        }else {
            produto.setAtivo(dto.ativo());
        }

        produto.setCategoria(categoria);

        return  produto;
    }









}
