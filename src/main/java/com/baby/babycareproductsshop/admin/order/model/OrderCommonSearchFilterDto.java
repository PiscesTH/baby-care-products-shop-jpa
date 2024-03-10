package com.baby.babycareproductsshop.admin.order.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class OrderCommonSearchFilterDto {
    private long iuser;
    private int searchCategory;
    private String keyword;
    private int dateFl;
    private int dateCategory;
    private String startDate;
    private String endDate;
    private int processState;
    private int payCategory;
    private int sort;
    private long offSet;
    private int size;
}
