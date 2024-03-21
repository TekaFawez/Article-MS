package com.inssolutions.articles.mapper;

import com.inssolutions.articles.dto.CategoryDTO;
import com.inssolutions.articles.dto.ProduitDTO;
import com.inssolutions.articles.entity.Category;
import com.inssolutions.articles.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;

@Mapper(uses = CategoryMapper.class)
public interface ProduitMapper {
    ProduitMapper produitMapper = Mappers.getMapper(ProduitMapper.class);
  //  @Mapping(target = "category" ,source = "categoryDto" )
    Produit mapToProduit(ProduitDTO produitDTO);
    //@Mapping(target = "categoryDto" ,source = "category" )

    ProduitDTO mapToProduitDTO(Produit produit);

}
