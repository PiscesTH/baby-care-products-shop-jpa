package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasketEntity is a Querydsl query type for BasketEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBasketEntity extends EntityPathBase<BasketEntity> {

    private static final long serialVersionUID = -635628815L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasketEntity basketEntity = new QBasketEntity("basketEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QBasketIds id;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> productCount = createNumber("productCount", Integer.class);

    public final QProductEntity productEntity;

    public final com.baby.babycareproductsshop.entity.user.QUserEntity userEntity;

    public QBasketEntity(String variable) {
        this(BasketEntity.class, forVariable(variable), INITS);
    }

    public QBasketEntity(Path<? extends BasketEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasketEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasketEntity(PathMetadata metadata, PathInits inits) {
        this(BasketEntity.class, metadata, inits);
    }

    public QBasketEntity(Class<? extends BasketEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QBasketIds(forProperty("id")) : null;
        this.productEntity = inits.isInitialized("productEntity") ? new QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new com.baby.babycareproductsshop.entity.user.QUserEntity(forProperty("userEntity")) : null;
    }

}

