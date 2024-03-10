package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.entity.order.OrderDetailsEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.baby.babycareproductsshop.entity.order.QOrderDetailsEntity.orderDetailsEntity;
import static com.baby.babycareproductsshop.entity.product.QProductEntity.productEntity;

@Slf4j
@RequiredArgsConstructor
public class AdminOrderDetailsQdlsRepositoryImpl implements AdminOrderDetailsQdlsRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // 주문 pk에 해당하는 주문 상세 정보를 모두 가져옴
    @Override
    public List<OrderDetailsEntity> findAll(long iorder) {
        JPAQuery<OrderDetailsEntity> jpaQuery = jpaQueryFactory
                .select(orderDetailsEntity)
                .from(orderDetailsEntity)
                .join(productEntity)
                .on(orderDetailsEntity.productEntity.iproduct.eq(productEntity.iproduct),
                         productEntity.iproduct.eq(orderDetailsEntity.productEntity.iproduct))
                .where(orderDetailsEntity.orderEntity.iorder.eq(iorder));

        return jpaQuery.fetch();
    }
}
