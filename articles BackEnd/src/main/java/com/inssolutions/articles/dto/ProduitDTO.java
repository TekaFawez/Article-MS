package com.inssolutions.articles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {
    private long id;
    private String productName;
    private long productPrice;
    private int productQty;
    private Long categoryId;



}
