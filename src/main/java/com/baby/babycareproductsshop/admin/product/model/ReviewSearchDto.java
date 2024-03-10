package com.baby.babycareproductsshop.admin.product.model;

import lombok.Data;

@Data
public class ReviewSearchDto {
    private String keyword;

    private Long iproduct;

    private Long imain;

    private Long imiddle;

    private int sortBy;
    private int page;

}
