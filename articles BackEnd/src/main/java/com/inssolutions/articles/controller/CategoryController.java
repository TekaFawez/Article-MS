package com.inssolutions.articles.controller;

import com.inssolutions.articles.dto.CategoryDTO;
import com.inssolutions.articles.dto.ProduitDTO;
import com.inssolutions.articles.sercvice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

   final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO saveCategory = categoryService.addCategory(categoryDTO).getBody();
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categoryDTOS =categoryService.findAllCategory().getBody();
        return new ResponseEntity<>(categoryDTOS,HttpStatus.OK);
    }
    @GetMapping("/getCategory/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById (@PathVariable("id")  Long id){
        CategoryDTO categoryDTO = categoryService.findCategoryById(id).getBody();
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }


    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Long id) {
        ResponseEntity<CategoryDTO> updateCategory = categoryService.updateCategory(id,categoryDTO);
        return new ResponseEntity<>(updateCategory.getBody(), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}
