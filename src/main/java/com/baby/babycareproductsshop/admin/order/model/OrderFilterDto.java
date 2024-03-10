package com.baby.babycareproductsshop.admin.order.model;

import lombok.Data;

@Data
public class OrderFilterDto {
    private int processState;
    private int dateCategory;
    private int searchCategory;
    private String keyword;
    private String startDate;
    private String endDate;
    private int dateFl;
    private int payCategory;
    private int page;
    private int sort;
}
