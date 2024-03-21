package com.inssolutions.articles.sercvice;

import com.inssolutions.articles.dto.CategoryDTO;
import com.inssolutions.articles.dto.ProduitDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO);

    ResponseEntity<CategoryDTO> findCategoryById(Long id) ;
    ResponseEntity<List<CategoryDTO>> findAllCategory() ;


    void deleteCategory(Long id);
    ResponseEntity<CategoryDTO> updateCategory(Long id,CategoryDTO categoryDTO);
}
