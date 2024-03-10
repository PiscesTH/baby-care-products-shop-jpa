package com.baby.babycareproductsshop.admin.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderBatchProcessDto {
    private List<Integer> iorders;
    private int processState;
}
