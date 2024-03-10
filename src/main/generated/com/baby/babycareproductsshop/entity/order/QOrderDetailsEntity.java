package com.baby.babycareproductsshop.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderDetailsEntity is a Querydsl query type for OrderDetailsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderDetailsEntity extends EntityPathBase<OrderDetailsEntity> {

    private static final long serialVersionUID = 155590976L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderDetailsEntity orderDetailsEntity = new QOrderDetailsEntity("orderDetailsEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> idetails = createNumber("idetails", Long.class);

    public final QOrderEntity orderEntity;

    public final NumberPath<Integer> productCnt = createNumber("productCnt", Integer.class);

    public final com.baby.babycareproductsshop.entity.product.QProductEntity productEntity;

    public final NumberPath<Integer> productPrice = createNumber("productPrice", Integer.class);

    public final NumberPath<Integer> productTotalPrice = createNumber("productTotalPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> refundedAt = createDateTime("refundedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> refundFl = createNumber("refundFl", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOrderDetailsEntity(String variable) {
        this(OrderDetailsEntity.class, forVariable(variable), INITS);
    }

    public QOrderDetailsEntity(Path<? extends OrderDetailsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderDetailsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderDetailsEntity(PathMetadata metadata, PathInits inits) {
        this(OrderDetailsEntity.class, metadata, inits);
    }

    public QOrderDetailsEntity(Class<? extends OrderDetailsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderEntity = inits.isInitialized("orderEntity") ? new QOrderEntity(forProperty("orderEntity"), inits.get("orderEntity")) : null;
        this.productEntity = inits.isInitialized("productEntity") ? new com.baby.babycareproductsshop.entity.product.QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
    }

}

