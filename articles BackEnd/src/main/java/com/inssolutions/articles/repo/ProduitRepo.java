package com.inssolutions.articles.repo;

import com.inssolutions.articles.entity.Category;
import com.inssolutions.articles.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit,Long> {

    List<Produit> findByCategoryId(@Param("categoryId") Long categoryId);

    List<Produit> findByCategoryIdIn(List<Long> categoryIds);
}
