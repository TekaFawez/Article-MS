package com.inssolutions.articles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue
    @Column(name = "ID")
     private long id;

    private String productName ;

    private long productPrice ;
    private int productQty;
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

}
