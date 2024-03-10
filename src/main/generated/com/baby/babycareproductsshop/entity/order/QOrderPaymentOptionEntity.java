package com.baby.babycareproductsshop.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderPaymentOptionEntity is a Querydsl query type for OrderPaymentOptionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderPaymentOptionEntity extends EntityPathBase<OrderPaymentOptionEntity> {

    private static final long serialVersionUID = 202910233L;

    public static final QOrderPaymentOptionEntity orderPaymentOptionEntity = new QOrderPaymentOptionEntity("orderPaymentOptionEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> ipaymentOption = createNumber("ipaymentOption", Long.class);

    public final StringPath paymentOption = createString("paymentOption");

    public QOrderPaymentOptionEntity(String variable) {
        super(OrderPaymentOptionEntity.class, forVariable(variable));
    }

    public QOrderPaymentOptionEntity(Path<? extends OrderPaymentOptionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderPaymentOptionEntity(PathMetadata metadata) {
        super(OrderPaymentOptionEntity.class, metadata);
    }

}

