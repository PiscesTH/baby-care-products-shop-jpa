package com.baby.babycareproductsshop.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = 1397231560L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final StringPath addressNm = createString("addressNm");

    public final StringPath adminMemo = createString("adminMemo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> deleteFl = createNumber("deleteFl", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> deliveredAt = createDateTime("deliveredAt", java.time.LocalDateTime.class);

    public final StringPath deliveryRequest = createString("deliveryRequest");

    public final DateTimePath<java.time.LocalDateTime> depositedAt = createDateTime("depositedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath fullAddress = createString("fullAddress");

    public final NumberPath<Long> iorder = createNumber("iorder", Long.class);

    public final QOrderPaymentOptionEntity orderPaymentOptionEntity;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> processState = createNumber("processState", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.baby.babycareproductsshop.entity.user.QUserAddressEntity userAddressEntity;

    public final com.baby.babycareproductsshop.entity.user.QUserEntity userEntity;

    public QOrderEntity(String variable) {
        this(OrderEntity.class, forVariable(variable), INITS);
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderEntity(PathMetadata metadata, PathInits inits) {
        this(OrderEntity.class, metadata, inits);
    }

    public QOrderEntity(Class<? extends OrderEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderPaymentOptionEntity = inits.isInitialized("orderPaymentOptionEntity") ? new QOrderPaymentOptionEntity(forProperty("orderPaymentOptionEntity")) : null;
        this.userAddressEntity = inits.isInitialized("userAddressEntity") ? new com.baby.babycareproductsshop.entity.user.QUserAddressEntity(forProperty("userAddressEntity"), inits.get("userAddressEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new com.baby.babycareproductsshop.entity.user.QUserEntity(forProperty("userEntity")) : null;
    }

}

