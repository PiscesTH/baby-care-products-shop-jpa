package com.baby.babycareproductsshop.admin.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderSmallFilterDto {
    @JsonIgnore
    private int processState;
    @JsonIgnore
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
