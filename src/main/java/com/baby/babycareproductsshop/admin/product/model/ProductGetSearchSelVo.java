package com.baby.babycareproductsshop.admin.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductGetSearchSelVo {
    private String productNm;
    private Long iproduct;
    private int price;
    private int remainedCnt;
    private Long imain;
    private Long imiddle;
    private String productDetails;
    private String adminMemo;
    private int recommandAge;
    private List<String> productPic;
    private long totalCount;

}
