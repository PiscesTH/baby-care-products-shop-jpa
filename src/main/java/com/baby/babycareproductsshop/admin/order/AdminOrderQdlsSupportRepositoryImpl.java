package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.admin.order.model.OrderCommonSearchFilterDto;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.entity.order.QOrderDetailsEntity;
import com.baby.babycareproductsshop.entity.order.QOrderEntity;
import com.baby.babycareproductsshop.entity.product.QProductEntity;
import com.baby.babycareproductsshop.entity.refund.QRefundEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Slf4j
@RequiredArgsConstructor
public class AdminOrderQdlsSupportRepositoryImpl extends AdminOrderSearchCondition {
    protected final JPAQueryFactory jpaQueryFactory;
    protected final QOrderEntity orderEntity = new QOrderEntity("orderEntity");
    protected final QOrderDetailsEntity orderDetailsEntity = new QOrderDetailsEntity("orderDetailsEntity");
    protected final QProductEntity productEntity = new QProductEntity("productEntity");
    protected final QRefundEntity refundEntity = new QRefundEntity("refundEntity");

    /**
     * @param dto 관리자 주문 페이지 내 공통 검색 필터 통합 dto
     */
    protected BooleanBuilder commonSearchFilter(OrderCommonSearchFilterDto dto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        return booleanBuilder
                .and(targetIuser(dto.getIuser())) // 특정 회원 검색
                .and(targetKeyword(dto.getSearchCategory(), dto.getKeyword())) // 검색 카테고리 + 검색어
                .and(dateSelectSearch(dto.getDateFl())) // 기간 선택
                .and(dateRangeSearch(dto.getDateCategory(), dto.getStartDate(), dto.getEndDate())) // 기간 검색
                .and(ipaymentOptionEq(dto.getPayCategory())) // 결제 방법
                .and(processStateEq(dto.getProcessState())); // 주문 처리 상태
    }

    /**
     * @param iuser - 회원 PK
     */
    private BooleanExpression targetIuser(long iuser) {
        return !Utils.isNotNull(iuser) ? null : orderEntity.userEntity.iuser.eq(iuser);
    }

    /**
     * @param searchCategory 1 - 주문 번호 2 - 상품 번호 3 - 유저 번호 4 - 주문자명 5 - 입금자명 6 - 수령자명 7 - 수령자 전화번호
     * @param keyword        검색 키워드
     */
    private BooleanExpression targetKeyword(int searchCategory, String keyword) {
        BooleanExpression booleanExpression = null;

        if (Utils.isNotNull(searchCategory) && !keyword.isBlank()) {
            long pk = Long.parseLong(keyword);
            switch (searchCategory) {
                case 1 -> booleanExpression = iorderEq(pk);
                case 2 -> booleanExpression = iproductEq(pk);
                case 3 -> booleanExpression = iuserEq(pk);
                case 4, 5, 6 -> booleanExpression = userNmEq(keyword);
                case 7 -> booleanExpression = phoneNumberEq(keyword);
                default -> {
                    return null;
                }
            }
        }
        return booleanExpression;
    }

    /**
     * @param processState 전체 - 0 | 입금 대기 - 1 | 배송 준비중 - 2 | 배송중 - 3 | 배송완료 - 4 | 취소 - 5 | 반품 - 6
     */
    private BooleanExpression processStateEq(int processState) {
        BooleanExpression booleanExpression = null;

        switch (processState) {
            case 1, 2, 3, 4 -> booleanExpression = orderEntity.processState.eq(processState);
            case 5 -> booleanExpression = orderEntity.deleteFl.eq(1);
            case 6 -> booleanExpression = refundEntity.irefund.isNotNull();
            default -> {
                return null;
            }
        }
        return booleanExpression;
    }

    /**
     * @param dateFl 전체 - 0 | 오늘 - 1 | 어제 - 2 | 일주일 - 3 | (지난 달 - 4 1개월 - 5) - 4 | 3개월 - 5 | 전체 - 6
     */
    private BooleanExpression dateSelectSearch(int dateFl) {
        BooleanExpression booleanExpression = null;

        switch (dateFl) {
            case 1 -> booleanExpression = orderEntity.createdAt.between(todayStartTime(), todayEndTime());
            case 2 -> booleanExpression = orderEntity.createdAt.between(yesterdayStartTime(), yesterdayEndTime());
            case 3 -> booleanExpression = orderEntity.createdAt.between(todayStartTime().minusDays(7), todayEndTime());
            case 4, 5 -> booleanExpression = orderEntity.createdAt.between(monthStartDay(), monthEndDay());
            case 6 ->
                    booleanExpression = orderEntity.createdAt.between(monthStartDay().minusMonths(3), monthEndDay().minusMonths(3));
            default -> {
                return null;
            }
        }
        return booleanExpression;
    }

    /**
     * @param dateCategory 전체 - 0 | 입금 완료일 - 1 | 배송 완료일 - 2 | 상품 취소일 - 3 | 상품 반품일 - 4
     * @param start        시작 일자
     * @param end          종료 일자
     */
    private BooleanExpression dateRangeSearch(int dateCategory, String start, String end) {
        BooleanExpression booleanExpression = null;

        if (!Utils.isNotNull(start) || start.isEmpty() && !Utils.isNotNull(end) || end.isEmpty()) {
            return null;
        } else {
            LocalDateTime startDate = LocalDate.parse(start).atTime(LocalTime.MIN);
            LocalDateTime endDate = LocalDate.parse(end).atTime(LocalTime.MAX);

            switch (dateCategory) {
                case 0 -> booleanExpression = orderEntity.createdAt.between(startDate, endDate);
                case 1 -> booleanExpression = orderEntity.depositedAt.between(startDate, endDate);
                case 2 -> booleanExpression = orderEntity.deliveredAt.between(startDate, endDate);
                case 3 -> booleanExpression = orderEntity.deletedAt.between(startDate, endDate);
                case 4 -> booleanExpression = orderDetailsEntity.refundedAt.between(startDate, endDate); // *
                default -> {
                    return null;
                }
            }
            return booleanExpression;
        }
    }

    protected BooleanExpression ipaymentOptionEq(int payCategory) {
        return !Utils.isNotNull(payCategory) ? null : orderEntity.orderPaymentOptionEntity.ipaymentOption.eq((long) payCategory);
    }

    /**
     * @param sort 0 - 주문일 역순 | 1 - 주문일 순 | 2 - 처리일 역순 | 3 - 처리일 순
     */
    protected OrderSpecifier orderListSort(int sort) {
        OrderSpecifier orderSpecifier = null;

        switch (sort) {
            case 0 -> orderSpecifier = orderEntity.iorder.desc(); // default
            case 1 -> orderSpecifier = orderEntity.iorder.asc();
            case 2 -> orderSpecifier = orderEntity.depositedAt.desc();
            case 3 -> orderSpecifier = orderEntity.depositedAt.asc();
            default -> {
                return null;
            }
        }
        return orderSpecifier;
    }

    // ------------------------------------------------------------------------------------------
    // 아래는 '검색 카테고리'에 해당되는 컬럼들 모음
    protected BooleanExpression iorderEq(long iorder) {
        return !Utils.isNotNull(iorder) ? null : orderEntity.iorder.eq(iorder);
    }

    protected BooleanExpression iproductEq(long iproduct) {
        return !Utils.isNotNull(iproduct) ? null : productEntity.iproduct.eq(iproduct);
    }

    protected BooleanExpression iuserEq(long iuser) {
        return !Utils.isNotNull(iuser) ? null : orderEntity.userEntity.iuser.eq(iuser);
    }

    protected BooleanExpression userNmEq(String userNm) {
        return !Utils.isNotNull(userNm) ? null : orderEntity.userEntity.nm.eq(userNm);
    }

    protected BooleanExpression phoneNumberEq(String phoneNumber) {
        return !Utils.isNotNull(phoneNumber) ? null : orderEntity.userEntity.phoneNumber.eq(phoneNumber);
    }

    // ------------------------------------------------------------------------------------------
    // 아래는 어제, 오늘 시작 시간과 끝 시간 조건문 모음
    private LocalDateTime todayStartTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    private LocalDateTime todayEndTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    private LocalDateTime yesterdayStartTime() {
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
    }

    private LocalDateTime yesterdayEndTime() {
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);
    }

    private LocalDateTime monthStartDay() {
        return LocalDateTime.of(YearMonth.now().minusMonths(1).atDay(1), LocalTime.MIN);
    }

    private LocalDateTime monthEndDay() {
        return LocalDateTime.of(YearMonth.now().minusMonths(1).atDay(31), LocalTime.MAX);
    }
}
