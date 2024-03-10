package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.admin.order.model.OrderFilterDto;
import com.baby.babycareproductsshop.admin.order.model.OrderMemoListDto;
import com.baby.babycareproductsshop.admin.order.model.OrderSmallFilterDto;
import com.baby.babycareproductsshop.admin.order.model.OrderUserFilterDto;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AdminOrderCommonValid {
    private final int PHONE_NUMBER_SEARCH_CATEGORY = 6;

    protected String stringIsNull(String str) {
        return str.equals("string") ? "" : str;
    }

    protected String phoneNumberCheck(int searchCategory, String keyword) {
        if (searchDataTypeCheck(searchCategory, keyword)) {
            if (searchCategory == PHONE_NUMBER_SEARCH_CATEGORY) {
                return phoneNumberFormatConverter(keyword);
            }
        }
        return null;
    }

    protected String phoneNumberFormatConverter(String phoneNumber) {
        String formatPhoneNumber = null;
        if (phoneNumber.length() == 11 || phoneNumber.length() == 13 && phoneNumber.startsWith("010") || phoneNumber.startsWith("011")) {
            if (phoneNumber.length() == 11) {
                formatPhoneNumber =
                                phoneNumber.substring(0, 3) + "-" +
                                phoneNumber.substring(3, 7) + "-" +
                                phoneNumber.substring(7);
            } else {
                String phoneNumberExpression = "^\\d{3}-\\d{3,4}-\\d{4}$";
                if (Pattern.matches(phoneNumberExpression, phoneNumber)) {
                    return phoneNumber;
                } else {
                    throw new RestApiException(AuthErrorCode.SEARCH_FAILED_ERROR);
                }
            }
        } else {
            throw new RestApiException(AuthErrorCode.SEARCH_FAILED_ERROR);
        }
        return formatPhoneNumber;
    }

    protected boolean searchDataTypeCheck(int searchCategory, String keyword) {
        boolean result = false;

        try {
            switch (searchCategory) {
                case 1, 2 -> {
                    Integer.valueOf(keyword);
                    return true;
                }
                case 3, 4, 5, 6 -> {
                    return !keyword.isBlank();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(AuthErrorCode.SEARCH_FAILED_ERROR);
        }
        return result;
    }

    protected OrderFilterDto commonValid(OrderFilterDto dto) {
        if (dto.getSearchCategory() == PHONE_NUMBER_SEARCH_CATEGORY) {
            dto.setKeyword(phoneNumberCheck(dto.getSearchCategory(), dto.getKeyword()));
        } else {
            dto.setKeyword(stringIsNull(dto.getKeyword()));
        }
        dto.setStartDate(stringIsNull(dto.getStartDate()));
        dto.setEndDate(stringIsNull(dto.getEndDate()));

        return dto;
    }

    protected OrderSmallFilterDto commonValid(OrderSmallFilterDto dto) {
        if (dto.getSearchCategory() == PHONE_NUMBER_SEARCH_CATEGORY) {
            dto.setKeyword(phoneNumberCheck(dto.getSearchCategory(), dto.getKeyword()));
        } else {
            dto.setKeyword(stringIsNull(dto.getKeyword()));
        }
        dto.setStartDate(stringIsNull(dto.getStartDate()));
        dto.setEndDate(stringIsNull(dto.getEndDate()));

        return dto;
    }

    protected OrderMemoListDto commonValid(OrderMemoListDto dto) {
        if (dto.getSearchCategory() == PHONE_NUMBER_SEARCH_CATEGORY) {
            dto.setKeyword(phoneNumberCheck(dto.getSearchCategory(), dto.getKeyword()));
        } else {
            dto.setKeyword(stringIsNull(dto.getKeyword()));
        }
        dto.setStartDate(stringIsNull(dto.getStartDate()));
        dto.setEndDate(stringIsNull(dto.getEndDate()));
        return dto;
    }

    protected OrderUserFilterDto commonValid(OrderUserFilterDto dto) {
        dto.setStartDate(stringIsNull(dto.getStartDate()));
        dto.setEndDate(stringIsNull(dto.getEndDate()));

        return dto;
    }
}