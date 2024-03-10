package com.baby.babycareproductsshop.admin.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminRefundReturnDto {
    @Schema(title = "연도")
    private int year;
    @Schema(title = "월")
    private int month;
}
