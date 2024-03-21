package com.inssolutions.articles.controller;

import com.inssolutions.articles.dto.CategoryDTO;
import com.inssolutions.articles.dto.ProduitDTO;
import com.inssolutions.articles.sercvice.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
@CrossOrigin
public class ProduitController {


   final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }
    @GetMapping("/getAllProduit")
    public ResponseEntity<List<ProduitDTO>> getAllProduit(){
        List<ProduitDTO> produitDTOS =produitService.findAllProduit().getBody();
        return new ResponseEntity<>(produitDTOS,HttpStatus.OK);
    }
    @PostMapping("/addProduit")
    public ResponseEntity<ProduitDTO> addProduit(@RequestBody ProduitDTO produitDTO){
        ProduitDTO saveProduit = produitService.addProduit(produitDTO).getBody();
        return new ResponseEntity<>(saveProduit, HttpStatus.CREATED);
    }
    @GetMapping("/getProduit/{id}")
    public ResponseEntity<ProduitDTO> getProduitById (@PathVariable("id")  Long id){
        ProduitDTO produit = produitService.findById(id).getBody();
        return new ResponseEntity<>(produit,HttpStatus.OK);
    }
    @PutMapping("/updateProduit/{id}")
    public ResponseEntity<ProduitDTO> updateProduit(@RequestBody ProduitDTO produitDTO, @PathVariable("id") Long id) {
        ResponseEntity<ProduitDTO> updateProd = produitService.updateProduit(id,produitDTO);
        return new ResponseEntity<>(updateProd.getBody(), HttpStatus.OK);
    }
    @GetMapping("/getByCategory/{id}")
    public ResponseEntity<List<ProduitDTO>> getProduitByCategory(@PathVariable("id") Long id){
        ResponseEntity<List<ProduitDTO>> produitsByCategory = produitService.findByCategoryId(id);
        return new ResponseEntity<>(produitsByCategory.getBody(),HttpStatus.OK);

    }

    @GetMapping("/getCategoryName/{name}")
    public ResponseEntity<List<ProduitDTO>> getProduitByCategoryName(@PathVariable("name") String name){
        ResponseEntity<List<ProduitDTO>> produitsByCategory = produitService.findByCategoryNameStartingWith(name);
        return new ResponseEntity<>(produitsByCategory.getBody(), HttpStatus.OK);
    }
    @DeleteMapping("/deleteProduit/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

}
