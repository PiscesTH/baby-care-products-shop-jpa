package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductEntity is a Querydsl query type for ProductEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductEntity extends EntityPathBase<ProductEntity> {

    private static final long serialVersionUID = -1002951414L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductEntity productEntity = new QProductEntity("productEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final StringPath adminMemo = createString("adminMemo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> delFl = createNumber("delFl", Integer.class);

    public final NumberPath<Long> imain = createNumber("imain", Long.class);

    public final NumberPath<Long> imiddle = createNumber("imiddle", Long.class);

    public final NumberPath<Long> iproduct = createNumber("iproduct", Long.class);

    public final QProductMiddleCategoryEntity middleCategoryEntity;

    public final NumberPath<Integer> newFl = createNumber("newFl", Integer.class);

    public final NumberPath<Integer> popFl = createNumber("popFl", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productDetails = createString("productDetails");

    public final StringPath productNm = createString("productNm");

    public final NumberPath<Integer> rcFl = createNumber("rcFl", Integer.class);

    public final NumberPath<Integer> recommandAge = createNumber("recommandAge", Integer.class);

    public final NumberPath<Integer> remainedCnt = createNumber("remainedCnt", Integer.class);

    public final StringPath repPic = createString("repPic");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QProductEntity(String variable) {
        this(ProductEntity.class, forVariable(variable), INITS);
    }

    public QProductEntity(Path<? extends ProductEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductEntity(PathMetadata metadata, PathInits inits) {
        this(ProductEntity.class, metadata, inits);
    }

    public QProductEntity(Class<? extends ProductEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.middleCategoryEntity = inits.isInitialized("middleCategoryEntity") ? new QProductMiddleCategoryEntity(forProperty("middleCategoryEntity"), inits.get("middleCategoryEntity")) : null;
    }

}

