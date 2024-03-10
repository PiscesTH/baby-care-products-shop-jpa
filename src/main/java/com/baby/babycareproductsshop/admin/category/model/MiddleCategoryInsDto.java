package com.baby.babycareproductsshop.admin.category.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MiddleCategoryInsDto {
    @Schema(title = "1차 카테고리 PK")
    private long imain;
    @Schema(title = "2차 카테고리명")
    private String middleCategory;
}
