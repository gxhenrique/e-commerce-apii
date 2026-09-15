package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.dto.categoria.CreateCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.ResponseCategoriaDTO;
import com.projeto.ecommerceapii.entity.Categoria;
import com.projeto.ecommerceapii.exception.BadRequestException;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.repository.CategoriaRepository;
import com.projeto.ecommerceapii.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseCategoriaDTO create(CreateCategoriaDTO dto) throws BadRequestException {

        if (categoriaRepository.findByNome(dto.nome()).isPresent()){
            throw new BadRequestException("Categoria já existe");
        }

        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(dto.nome());

        categoriaRepository.save(novaCategoria);

        return new ResponseCategoriaDTO(novaCategoria.getId(),novaCategoria.getNome());

    }


    public void delete(Long id) throws BadRequestException {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encotrada"));

        boolean produtoCategoriaId = produtoRepository.existsByCategoriaId(categoria.getId());

        if(produtoCategoriaId){
            throw new BadRequestException("Categoria vinculado a um produto");
        }

        categoriaRepository.delete(categoria);
    }


}
