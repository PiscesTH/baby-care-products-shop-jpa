package com.baby.babycareproductsshop.admin.product.model;

import java.time.LocalDateTime;

public interface Statist {
    LocalDateTime getUpdatedAt();
    default int getValue(String date) {
        return Integer.parseInt(date.substring(date.lastIndexOf("-")));
    }
}
