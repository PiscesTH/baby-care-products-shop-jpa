package com.baby.babycareproductsshop.admin.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class AdminProductUptDto {
    @JsonIgnore
    private Long iproduct;

    private Long imain; // 대분류

    private Long imiddle; //중분류

    private String productNm; // 상품이름

    @JsonIgnore
    private String productDetails; // 상품 설명

    private int recommendedAge; //나이

    private String adminMemo;

    private int price; // 가격

    @JsonIgnore
    private String repPic; // 상품대표사진


    private int remainedCnt; //재고
    private int newFl;
    private int popFl;

    private List<String> delPics;






    //private List<String> pics;
}





