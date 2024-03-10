package com.baby.babycareproductsshop.admin.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderUserFilterDto {
    @JsonIgnore
    private long iuser;
    private int dateFl;
    private String startDate;
    private String endDate;
    private int processState;
    private int page;
    private int sort;
}
