package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductPicEntity is a Querydsl query type for ProductPicEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductPicEntity extends EntityPathBase<ProductPicEntity> {

    private static final long serialVersionUID = -608271482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductPicEntity productPicEntity = new QProductPicEntity("productPicEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> iproduct = createNumber("iproduct", Long.class);

    public final QProductEntity productEntity;

    public final StringPath productPic = createString("productPic");

    public QProductPicEntity(String variable) {
        this(ProductPicEntity.class, forVariable(variable), INITS);
    }

    public QProductPicEntity(Path<? extends ProductPicEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductPicEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductPicEntity(PathMetadata metadata, PathInits inits) {
        this(ProductPicEntity.class, metadata, inits);
    }

    public QProductPicEntity(Class<? extends ProductPicEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productEntity = inits.isInitialized("productEntity") ? new QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
    }

}

