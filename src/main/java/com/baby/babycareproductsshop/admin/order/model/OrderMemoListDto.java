package com.baby.babycareproductsshop.admin.order.model;

import lombok.Data;

@Data
public class OrderMemoListDto {
    private int searchCategory;
    private String keyword;
    private String startDate;
    private String endDate;
    private int dateFl;
    private int page;
    private int sort;
}
