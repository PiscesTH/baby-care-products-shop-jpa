package com.baby.babycareproductsshop.admin.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductUptDto {


//        @JsonIgnore
//        private Long iproduct;

        private Long imain; // 대분류

        private Long imiddle; //중분류

        private String productNm; // 상품이름

        @JsonIgnore
        private String productDetails; // 상품 설명

        private int recommendedAge; //나이

        private int price; // 가격

        @JsonIgnore
        private String repPic; // 상품대표사진

        private int remainedCount; //재고
        //
//        private int newFl; // 신상품
//
//        private int rcFl; // 추천상품
//
//        private int popFl; // 인기상품
        private String adminMemo;

        //private List<String> pics;
}
