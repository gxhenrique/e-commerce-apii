package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.dto.categoria.CreateCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.ResponseCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.UpdateCategoriaDTO;
import com.projeto.ecommerceapii.entity.Categoria;
import com.projeto.ecommerceapii.exception.BadRequestException;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.mapper.categoria.CategoriaMapper;
import com.projeto.ecommerceapii.repository.CategoriaRepository;
import com.projeto.ecommerceapii.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {


    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public ResponseCategoriaDTO findById(Long id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encotrada"));
        return categoriaMapper.toResponse(categoria);
    }

    public Page<ResponseCategoriaDTO> finAll(Integer pagina, Integer itens){
        Page<Categoria> list = categoriaRepository.findAll(PageRequest.of(pagina,itens));
        return list.map(categoriaMapper::toResponse);
    }

    public ResponseCategoriaDTO create(CreateCategoriaDTO dto) throws BadRequestException {

        if (categoriaRepository.findByNome(dto.nome()).isPresent()){
            throw new BadRequestException("Categoria já existe");
        }

        Categoria categoria = categoriaMapper.toEntity(dto);
        categoriaRepository.save(categoria);

        return categoriaMapper.toResponse(categoria);

    }

    public void delete(Long id) throws BadRequestException {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encotrada"));

        if(produtoRepository.existsByCategoriaId(categoria.getId())){
            throw new BadRequestException("Categoria vinculado a um produto");
        }

        categoriaRepository.delete(categoria);
    }


    public ResponseCategoriaDTO update(Long id, UpdateCategoriaDTO dto){

        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Categoria não encotrada"));

        categoriaMapper.toUpdate(categoria, dto);
        categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(categoria);
    }


}
