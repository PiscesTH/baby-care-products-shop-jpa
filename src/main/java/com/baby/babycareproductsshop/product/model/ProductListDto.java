package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "상품상세정보")
public class ProductListDto {
    @Schema(title = "중분류 PK")
    private int imiddle;

    @Schema(title = "대분류 PK")
    private int imain;

    @Schema(title = "정렬 값 (0 : 최신순, 1 :가격 높은순, 2 : 가격 낮은순)")
    private int sortBy;

    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount = 12;

    @Schema(title = "페이지",defaultValue = "1")
    public void setPage(int page) {
        this.startIdx = (page - 1) * rowCount;
    }


}
