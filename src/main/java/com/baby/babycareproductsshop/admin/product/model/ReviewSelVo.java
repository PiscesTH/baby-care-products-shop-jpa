package com.baby.babycareproductsshop.admin.product.model;

import lombok.Data;

@Data
public class ReviewSelVo {
    private int productScore;
    private String contents;
    private String userName;
    private String productNm;
    private int price;
    private String repPic;
    private Long iproduct;
}
