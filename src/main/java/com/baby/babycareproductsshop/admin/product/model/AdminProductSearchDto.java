package com.baby.babycareproductsshop.admin.product.model;

import lombok.Data;

@Data

public class AdminProductSearchDto {
    private String keyword;

    private Long iproduct;

    private Long imain;

    private Long imiddle;
    private int page;

//    private int newFl;
//    private int popFl;
//    private int rcFl;
}
