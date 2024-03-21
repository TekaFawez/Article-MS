package com.inssolutions.articles.sercvice.impl;

import com.inssolutions.articles.dto.CategoryDTO;
import com.inssolutions.articles.dto.ProduitDTO;
import com.inssolutions.articles.entity.Category;
import com.inssolutions.articles.entity.Produit;
import com.inssolutions.articles.mapper.CategoryMapper;
import com.inssolutions.articles.mapper.ProduitMapper;
import com.inssolutions.articles.repo.CategoryRepo;
import com.inssolutions.articles.sercvice.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

final    CategoryRepo categoryRepo;

    public CategoryServiceImp(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.CategoryMapper.mapToCategory(categoryDTO);
        Category saveCategory = categoryRepo.save(category);
        CategoryDTO saveCategoryDTO = CategoryMapper.CategoryMapper.mapToCategoryDTO(saveCategory);

        return ResponseEntity.ok(saveCategoryDTO);
    }

    @Override
    public ResponseEntity<CategoryDTO> findCategoryById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(CategoryMapper.CategoryMapper.mapToCategoryDTO(category.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> findAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(
                CategoryMapper.CategoryMapper::mapToCategoryDTO).collect(Collectors.toList());

        return ResponseEntity.ok(categoryDTOS);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> findCategory = categoryRepo.findById(id);
        if (findCategory.isPresent()) {
            Category category = findCategory.get();
            category.setName(categoryDTO.getName());

            Category updateCategory = categoryRepo.save(category);
            CategoryDTO categoryDTO1 = CategoryMapper.CategoryMapper.mapToCategoryDTO(updateCategory);
            return ResponseEntity.ok(categoryDTO1);
        } else {
            throw new EntityNotFoundException(" n'a pas été trouvé.");
        }
    }
}
