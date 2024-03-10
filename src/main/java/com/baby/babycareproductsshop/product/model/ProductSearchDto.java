package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Schema(title = "검색 시 필요한 데이터")
public class ProductSearchDto {
    @Schema(title = "검색어")
    private String keyword;

    @Schema(title = "최솟값")
    private int minPrice;

    @Schema(title = "최댓값")
    private int maxPrice;

    @Schema(title = "정렬 값 (0 : 최신순, 1 :가격 높은순, 2 : 가격 낮은순)")
    private int sortBy ;


   private List<ProductSearchCat> cat;




    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount = 12;

    @Schema(title = "페이징처리",defaultValue = "1")
    public void setPage(int page) {
        this.startIdx = (page - 1) * rowCount;
    }



}
