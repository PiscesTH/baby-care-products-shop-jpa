package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWishEntity is a Querydsl query type for WishEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWishEntity extends EntityPathBase<WishEntity> {

    private static final long serialVersionUID = 247981842L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWishEntity wishEntity = new QWishEntity("wishEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.baby.babycareproductsshop.entity.user.QUserEntity entity;

    public final QWishIds ids;

    public final QProductEntity productEntity;

    public QWishEntity(String variable) {
        this(WishEntity.class, forVariable(variable), INITS);
    }

    public QWishEntity(Path<? extends WishEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWishEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWishEntity(PathMetadata metadata, PathInits inits) {
        this(WishEntity.class, metadata, inits);
    }

    public QWishEntity(Class<? extends WishEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.entity = inits.isInitialized("entity") ? new com.baby.babycareproductsshop.entity.user.QUserEntity(forProperty("entity")) : null;
        this.ids = inits.isInitialized("ids") ? new QWishIds(forProperty("ids")) : null;
        this.productEntity = inits.isInitialized("productEntity") ? new QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
    }

}

