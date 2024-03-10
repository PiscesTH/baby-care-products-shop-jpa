package com.baby.babycareproductsshop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class ProcessStateCodeToStringConverter implements Converter<Integer, String> { // key - 받을 데이터 value - 보낼 데이터 명시
    @Override // 컨버터 변환 메소드 오버라이딩
    public String convert(Integer source) {
        String processStateMsg = null;

        switch (source) {
            case 0:
                processStateMsg = ProcessState.ORDER_CONFIRM.getProcessState();
                break;
            case 1:
                processStateMsg = ProcessState.BEFORE_DEPOSIT.getProcessState();
                break;
            case 2:
                processStateMsg = ProcessState.DELIVER_IN_PROGRESS.getProcessState();
                break;
            case 3:
                processStateMsg = ProcessState.ON_DELIVERY.getProcessState();
                break;
            case 4:
                processStateMsg = ProcessState.DELIVER_SUCCESS.getProcessState();
                break;
            case 5:
                processStateMsg = ProcessState.ORDER_CANCEL.getProcessState();
                break;
            case 6:
                processStateMsg = ProcessState.REFUND.getProcessState();
                break;
        }
        return processStateMsg;
    }
}
