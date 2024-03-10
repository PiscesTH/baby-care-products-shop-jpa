package com.baby.babycareproductsshop.admin.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminProductSearchSelVo {
    private String productNm;
    private Long iproduct;
    private int price;
    private String repPic;
    private int status;
    private long totalCount;
    //private Long totalCount;
//    private Long imain;
//    private Long imiddle;


}
