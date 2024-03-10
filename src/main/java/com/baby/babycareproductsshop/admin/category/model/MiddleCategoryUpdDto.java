package com.baby.babycareproductsshop.admin.category.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MiddleCategoryUpdDto {
    @Schema(title = "2차 카테고리 대리키")
    private long candidateKey;
//    @Schema(title = "1차 카테고리 PK")
//    private long imain;
//    @Schema(title = "2차 카테고리 PK")
//    private long imiddle;
    @Schema(title = "2차 카테고리명")
    private String middleCategory;
}
