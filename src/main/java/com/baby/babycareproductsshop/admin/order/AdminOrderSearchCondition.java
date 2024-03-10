package com.baby.babycareproductsshop.admin.order;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import static com.baby.babycareproductsshop.entity.order.QOrderDetailsEntity.orderDetailsEntity;
import static com.baby.babycareproductsshop.entity.order.QOrderEntity.orderEntity;
import static com.baby.babycareproductsshop.entity.user.QUserEntity.userEntity;

public abstract class AdminOrderSearchCondition {

    protected BooleanExpression yearEq(int year) {
        return year == 0 ? null : orderEntity.createdAt.year().eq(year);
    }

    protected BooleanExpression monthEq(int month) {
        return month == 0 ? null : orderEntity.createdAt.month().eq(month);
    }

    protected BooleanExpression yearEqFromOrderDetail(int year) {
        return year == 0 ? null : orderDetailsEntity.createdAt.year().eq(year);
    }

    protected BooleanExpression monthEqFromOrderDetail(int month) {
        return month == 0 ? null : orderDetailsEntity.createdAt.month().eq(month);
    }

    protected BooleanExpression goeCreatedAt(LocalDateTime condition) {
        return condition == null ? null : orderEntity.createdAt.goe(condition);
    }

    protected BooleanExpression betweenCreatedAt(LocalDateTime before, LocalDateTime after) {
        return before == null || after == null ? null : orderEntity.createdAt.between(before, after);
    }

    protected BooleanExpression betweenCreatedAt(LocalDateTime before) {
        return betweenCreatedAt(before, LocalDateTime.now());
    }

    protected BooleanExpression whereYear(int year) {
        return year == 0 ? null : orderEntity.createdAt.year().eq(year);
    }

    protected BooleanExpression whereMonth(int month) {
        return month == 0 ? null : orderEntity.createdAt.month().eq(month);
    }

    protected StringTemplate transformDate(Object object) {
        return Expressions.stringTemplate("DATE_FORMAT({0},'{1s}')", object, ConstantImpl.create("%Y-%m-%d"));
    }

    protected BooleanExpression processStateNq(int processState) {
        return processState == 0 ? null : orderEntity.processState.ne(processState);
    }

    protected BooleanExpression deleteFlEq(int deleteFl) {
        return orderEntity.deleteFl.eq(deleteFl);
    }
}
