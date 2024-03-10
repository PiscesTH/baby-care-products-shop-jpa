package com.baby.babycareproductsshop.admin.category.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MainCategoryUpdDto {
    @Schema(title = "1차 카테고리 PK")
    private long imain;
    @Schema(title = "1차 카테고리명")
    private String mainCategory;
}
