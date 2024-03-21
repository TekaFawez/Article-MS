package com.inssolutions.articles.sercvice.impl;

import com.inssolutions.articles.dto.CategoryDTO;
import com.inssolutions.articles.dto.ProduitDTO;
import com.inssolutions.articles.entity.Category;
import com.inssolutions.articles.entity.Produit;
import com.inssolutions.articles.exception.CategoryNotFoundException;
import com.inssolutions.articles.mapper.CategoryMapper;
import com.inssolutions.articles.mapper.ProduitMapper;
import com.inssolutions.articles.repo.CategoryRepo;
import com.inssolutions.articles.repo.ProduitRepo;
import com.inssolutions.articles.sercvice.ProduitService;
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

public class ProduitServiceImp implements ProduitService {

   final CategoryRepo categoryRepo;


   final ProduitRepo produitRepo;

    public ProduitServiceImp(CategoryRepo categoryRepo, ProduitRepo produitRepo) {
        this.categoryRepo = categoryRepo;
        this.produitRepo = produitRepo;
    }

    @Override

   /* public ResponseEntity<ProduitDTO> addProduit(ProduitDTO produitDTO) {
        Produit produit = ProduitMapper.produitMapper.mapToProduit(produitDTO);

        if (categoryRepo.existsById(produitDTO.getCategoryDto().getId())) {

            Produit saveProduit = produitRepo.save(produit);
            ProduitDTO saveProduitDTO = ProduitMapper.produitMapper.mapToProduitDTO(saveProduit);
           return ResponseEntity.ok(saveProduitDTO);} else {
           throw new EntityNotFoundException("La catégorie avec l'ID " + produitDTO.getCategoryDto().getId() + " n'existe pas.");
      }
    }*/
    public ResponseEntity<ProduitDTO> addProduit(ProduitDTO produitDTO) {
        if (categoryRepo.existsById(produitDTO.getCategoryId())) {
            Produit produit = ProduitMapper.produitMapper.mapToProduit(produitDTO);
            Produit saveProduit = produitRepo.save(produit);
            ProduitDTO saveProduitDTO = ProduitMapper.produitMapper.mapToProduitDTO(saveProduit);
            return ResponseEntity.ok(saveProduitDTO);
        } else {
            throw new EntityNotFoundException("La catégorie avec l'ID " + produitDTO.getCategoryId() + " n'existe pas.");
        }
    }
    @Override
    public ResponseEntity<List<ProduitDTO>> findAllProduit() {
        List<Produit> produits = produitRepo.findAll();
        List<ProduitDTO> produitDTOs = produits.stream()
                .map(ProduitMapper.produitMapper::mapToProduitDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produitDTOs);
    }



    @Override
    public ResponseEntity<ProduitDTO> findById(Long id) {
       Optional <Produit> produit = produitRepo.findById(id);
        if(produit.isPresent()){
            return new ResponseEntity<>(ProduitMapper.produitMapper.mapToProduitDTO(produit.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<List<ProduitDTO>> findByCategoryId(Long categoryId) {
        List<Produit> produits = produitRepo.findByCategoryId(categoryId);

        if (produits.isEmpty()) {
            throw new CategoryNotFoundException("Catégorie non trouvée avec l'ID : " + categoryId);
        }

        List<ProduitDTO> produitDTOs = produits.stream()
                .map(ProduitMapper.produitMapper::mapToProduitDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(produitDTOs);
    }
    @Override
    public ResponseEntity<List<ProduitDTO>> findByCategoryNameStartingWith(String categoryName) {
        // Trouver les catégories dont le nom commence par la chaîne spécifiée
        List<Category> categories = categoryRepo.findByNameStartingWith(categoryName);

        // Extraire les IDs des catégories
        List<Long> categoryIds = categories.stream().map(Category::getId).collect(Collectors.toList());

        // Trouver les produits associés à ces catégories
        List<Produit> produits = produitRepo.findByCategoryIdIn(categoryIds);

        // Mapper les produits en DTOs
        List<ProduitDTO> produitDTOs = produits.stream()
                .map(ProduitMapper.produitMapper::mapToProduitDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(produitDTOs);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepo.deleteById(id);

    }

    @Override
   /* public ResponseEntity<ProduitDTO> updateProduit(Long id ,ProduitDTO produitDTO) {
        Optional<Produit> existingProduit = produitRepo.findById(id);
        if (existingProduit.isPresent()) {

            Produit produit = existingProduit.get();
            produit.setProductName(produitDTO.getProductName());
            Category category = CategoryMapper.CategoryMapper.mapToCategory(produitDTO.getCategoryDto());

            produit.setCategory(category);
            produit.setProductQty(produitDTO.getProductQty());
            produit.setProductPrice(produitDTO.getProductPrice());

            Produit updatedProduit = produitRepo.save(produit);
           ProduitDTO produitDTO1= ProduitMapper.produitMapper.mapToProduitDTO(updatedProduit);

            return ResponseEntity.ok(produitDTO1);

        } else {
            throw new EntityNotFoundException(" n'a pas été trouvé.");
        }
    }*/
    public ResponseEntity<ProduitDTO> updateProduit(Long id, ProduitDTO produitDTO) {
        Optional<Produit> existingProduit = produitRepo.findById(id);
        if (existingProduit.isPresent()) {
            Produit produit = existingProduit.get();
            produit.setProductName(produitDTO.getProductName());
            produit.setCategoryId(produitDTO.getCategoryId()); // Modifier l'ID de la catégorie

            produit.setProductQty(produitDTO.getProductQty());
            produit.setProductPrice(produitDTO.getProductPrice());

            Produit updatedProduit = produitRepo.save(produit);
            ProduitDTO produitDTO1 = ProduitMapper.produitMapper.mapToProduitDTO(updatedProduit);
            return ResponseEntity.ok(produitDTO1);
        } else {
            throw new EntityNotFoundException("Le produit avec l'ID " + id + " n'a pas été trouvé.");
        }
    }
    
}
