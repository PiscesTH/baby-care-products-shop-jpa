package com.baby.babycareproductsshop.common;

import com.baby.babycareproductsshop.admin.order.model.AdminSelOrderStatisticsDto;
import com.baby.babycareproductsshop.admin.order.model.StatisticsVo;
import com.baby.babycareproductsshop.admin.product.model.Statist;

import java.time.LocalDate;

public class Utils {
    public static boolean isNotNull(String str) {
        return str == null ? false : true;
    }

    public static boolean isNotNull(int num) {
        return num == 0 ? false : true;
    }

    public static boolean isNotNull(long num) {
        return num == 0 ? false : true;
    }

    public static boolean isNotNull(Object object) {
        return object == null ? false : true;
    }

    public static String getDate(int year, int month, StatisticsVo vo) {
        StringBuilder sb = new StringBuilder();
        return month != 0 ?
                sb.append(vo.getCreatedAt().getYear()).append("-").append(vo.getCreatedAt().getMonthValue()).append("-").append(vo.getCreatedAt().getDayOfMonth()).toString()
                : year != 0 ?
                sb.append(vo.getCreatedAt().getYear()).append("-").append(vo.getCreatedAt().getMonthValue()).toString()
                : sb.append(vo.getCreatedAt().getYear()).toString();
    }

    public static String getDate(int year, int month, Statist vo) {
        StringBuilder sb = new StringBuilder();
        return month != 0 ?
                sb.append(vo.getUpdatedAt().getYear()).append("-").append(vo.getUpdatedAt().getMonthValue()).append("-").append(vo.getUpdatedAt().getDayOfMonth()).toString()
                : year != 0 ?
                sb.append(vo.getUpdatedAt().getYear()).append("-").append(vo.getUpdatedAt().getMonthValue()).toString()
                : sb.append(vo.getUpdatedAt().getYear()).toString();
    }

    public static int getDaysOrMonths(int year, int month) {
        if (month != 0) {
            LocalDate date = LocalDate.of(year, month, 1);
            return date.lengthOfMonth();
        }
        return 12;
    }

    public static String getKey(int year, int month, int dayOrMonth) {
        StringBuilder sb = new StringBuilder();
        if (month == 0) {
            return sb.append(year).append("-").append(dayOrMonth).toString();
        }
        return sb.append(year).append("-").append(month).append("-").append(dayOrMonth).toString();
    }
}
