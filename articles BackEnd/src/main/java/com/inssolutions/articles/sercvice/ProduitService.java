package com.inssolutions.articles.sercvice;

import com.inssolutions.articles.dto.ProduitDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProduitService {
    ResponseEntity<ProduitDTO> addProduit(ProduitDTO produitDTO);

    ResponseEntity<ProduitDTO> findById(Long id) ;
    ResponseEntity<List<ProduitDTO>> findByCategoryId(Long categoryId);
    void deleteProduit(Long id);
    ResponseEntity<ProduitDTO> updateProduit(Long id,ProduitDTO produitDTO);


    ResponseEntity<List<ProduitDTO>> findAllProduit();

    ResponseEntity<List<ProduitDTO>> findByCategoryNameStartingWith(String nom);
}
