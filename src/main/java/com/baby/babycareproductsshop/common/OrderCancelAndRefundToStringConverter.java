package com.baby.babycareproductsshop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class OrderCancelAndRefundToStringConverter implements Converter<Integer, String> {
    @Override
    public String convert(Integer source) {
        String orderCancelAndRefund = null;

        switch (source) {
            case 1:
                orderCancelAndRefund = ProcessState.ORDER_CANCEL.getProcessState();
                break;
            case 2:
                orderCancelAndRefund = ProcessState.REFUND.getProcessState();
                break;
        }
        return orderCancelAndRefund;
    }
}