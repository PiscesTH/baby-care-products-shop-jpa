package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.AdminRefundReturnDto;
import com.baby.babycareproductsshop.entity.order.OrderDetailsEntity;
import com.baby.babycareproductsshop.entity.order.OrderEntity;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.baby.babycareproductsshop.entity.order.QOrderDetailsEntity.orderDetailsEntity;
import static com.baby.babycareproductsshop.entity.order.QOrderEntity.orderEntity;
import static com.baby.babycareproductsshop.entity.user.QUserEntity.userEntity;

@Slf4j
@RequiredArgsConstructor
public class OrderDetailsRepositoryImpl implements OrderDetailsQdslRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderDetailsEntity> refundReturnSelVo(AdminRefundReturnDto dto) {

            JPAQuery<OrderDetailsEntity> query = jpaQueryFactory.select(Projections.fields(OrderDetailsEntity.class,
                            orderDetailsEntity.refundFl.count().intValue().as("refundFl"),
                            orderDetailsEntity.updatedAt
                    )).where(orderDetailsEntity.refundFl.eq(1))
                    .from(orderDetailsEntity);
            if (dto.getMonth() != 0) {
                query.groupBy(transformDate(orderDetailsEntity.createdAt));
                query.having(whereYear(dto.getYear()), whereMonth(dto.getMonth()));
                return query.fetch();
            }
            query.groupBy(orderDetailsEntity.updatedAt.year(), orderDetailsEntity.updatedAt.month());
            query.having(whereYear(dto.getYear()));
            return query.fetch();
    }
    protected BooleanExpression whereYear(int year) {
        return year == 0 ? null : orderDetailsEntity.updatedAt.year().eq(year);
    }

    protected BooleanExpression whereMonth(int month) {
        return month == 0 ? null : orderDetailsEntity.updatedAt.month().eq(month);
    }
    protected StringTemplate transformDate(Object object) {
        return Expressions.stringTemplate("DATE_FORMAT({0},'{1s}')", object, ConstantImpl.create("%Y-%m-%d"));
    }
}
