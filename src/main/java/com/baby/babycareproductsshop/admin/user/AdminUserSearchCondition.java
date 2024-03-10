package com.baby.babycareproductsshop.admin.user;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import static com.baby.babycareproductsshop.entity.order.QOrderEntity.orderEntity;
import static com.baby.babycareproductsshop.entity.user.QUserEntity.userEntity;

public abstract class AdminUserSearchCondition {
    protected BooleanExpression whereUnregisteredFl(long unregisteredFl) {
        return userEntity.unregisterFl.eq(unregisteredFl);
    }

    protected BooleanExpression likeNm(String nm) {
        return StringUtils.hasText(nm) ? userEntity.nm.contains(nm) : null;
    }

    protected BooleanExpression likeEmail(String email) {
        return StringUtils.hasText(email) ? userEntity.email.contains(email) : null;
    }

    protected BooleanExpression goeCreatedAt(LocalDateTime condition) {
        return condition == null ? null : userEntity.createdAt.goe(condition);
    }

    protected BooleanExpression likePhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : userEntity.phoneNumber.contains(phoneNumber);
    }

    protected BooleanExpression betweenCreatedAt(LocalDateTime before, LocalDateTime after) {
        return before == null || after == null ? null : userEntity.createdAt.between(before, after);
    }

    protected BooleanExpression betweenCreatedAt(LocalDateTime before) {
        return betweenCreatedAt(before, LocalDateTime.now());
    }

    protected BooleanExpression whereYear(int year) {
        return year == 0 ? null : userEntity.createdAt.year().eq(year);
    }

    protected BooleanExpression whereMonth(int month) {
        return month == 0 ? null : userEntity.createdAt.month().eq(month);
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
