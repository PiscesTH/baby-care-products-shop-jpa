package com.baby.babycareproductsshop.admin.order.model;


import java.time.LocalDateTime;

public interface StatisticsVo {
    LocalDateTime getCreatedAt();
    default int getValue(String date) {
        return Integer.parseInt(date.substring(date.lastIndexOf("-")));
    }

}
