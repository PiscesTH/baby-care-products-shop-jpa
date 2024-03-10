package com.baby.babycareproductsshop.admin.category.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CategoryVo {
    private long imain;
    private String mainCategory;
    private List<MiddleCategoryVo> middleCategoryList;

    @Data
    @Builder
    @AllArgsConstructor
    public static class MiddleCategoryVo {
        private long candidateKey;
        private long imiddle;
        private String middleCategory;
    }
}
