package com.baby.babycareproductsshop.admin.product.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdminProductUptDate {
    private List<String> productPic;
    private Long imain;
    private Long imiddle;
    private String productNm;
    private int recommandAge;
    private String adminMemo;
    private int price;
    //private String repPic;
    private int remainedCnt;

}