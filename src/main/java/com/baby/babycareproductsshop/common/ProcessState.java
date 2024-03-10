package com.baby.babycareproductsshop.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProcessState {
    ORDER_CONFIRM(0, "구매확인"),
    BEFORE_DEPOSIT(1, "입금대기"),
    DELIVER_IN_PROGRESS(2, "배송준비중"),
    ON_DELIVERY(3, "배송중"),
    DELIVER_SUCCESS(4, "배송완료"),
    ORDER_CANCEL(5, "주문취소"),
    REFUND(6, "환불");

    private final int processStateNum;
    private final String processState;
}
