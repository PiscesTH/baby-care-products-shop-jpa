package com.baby.babycareproductsshop.entity.refund;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefundEntity is a Querydsl query type for RefundEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefundEntity extends EntityPathBase<RefundEntity> {

    private static final long serialVersionUID = -638734264L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefundEntity refundEntity = new QRefundEntity("refundEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final NumberPath<Integer> complateFl = createNumber("complateFl", Integer.class);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> irefund = createNumber("irefund", Long.class);

    public final com.baby.babycareproductsshop.entity.order.QOrderDetailsEntity orderDetailsEntity;

    public final NumberPath<Integer> refundCnt = createNumber("refundCnt", Integer.class);

    public final NumberPath<Integer> refundPrice = createNumber("refundPrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRefundEntity(String variable) {
        this(RefundEntity.class, forVariable(variable), INITS);
    }

    public QRefundEntity(Path<? extends RefundEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefundEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefundEntity(PathMetadata metadata, PathInits inits) {
        this(RefundEntity.class, metadata, inits);
    }

    public QRefundEntity(Class<? extends RefundEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderDetailsEntity = inits.isInitialized("orderDetailsEntity") ? new com.baby.babycareproductsshop.entity.order.QOrderDetailsEntity(forProperty("orderDetailsEntity"), inits.get("orderDetailsEntity")) : null;
    }

}

