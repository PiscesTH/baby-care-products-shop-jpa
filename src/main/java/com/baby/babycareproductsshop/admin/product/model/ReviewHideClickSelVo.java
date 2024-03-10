package com.baby.babycareproductsshop.admin.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewHideClickSelVo {
    private String nm; //작성자이름
    private String reqReviewPic; // 대표사진
    private String productNm; // 상품이름
    private String contents; // 리뷰내용
    private int productScore; // 별점

}
