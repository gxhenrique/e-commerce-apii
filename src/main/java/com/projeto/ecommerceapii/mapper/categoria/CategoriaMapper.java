package com.projeto.ecommerceapii.mapper.categoria;

import com.projeto.ecommerceapii.dto.categoria.CreateCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.ResponseCategoriaDTO;
import com.projeto.ecommerceapii.dto.categoria.UpdateCategoriaDTO;
import com.projeto.ecommerceapii.entity.Categoria;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    private final CategoriaRepository categoriaRepository;

    public CategoriaMapper(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseCategoriaDTO toResponse(Categoria categoria){
        return new ResponseCategoriaDTO(categoria.getId(), categoria.getNome());
    }

    public Categoria toEntity(CreateCategoriaDTO dto){
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        return categoria;
    }

    public void toUpdate(Categoria categoria, UpdateCategoriaDTO dto) {
        categoria.setNome(dto.nome());

    }
}
