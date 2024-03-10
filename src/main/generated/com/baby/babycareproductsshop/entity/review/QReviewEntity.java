package com.baby.babycareproductsshop.entity.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewEntity is a Querydsl query type for ReviewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewEntity extends EntityPathBase<ReviewEntity> {

    private static final long serialVersionUID = -2080238520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewEntity reviewEntity = new QReviewEntity("reviewEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    public final StringPath adminMemo = createString("adminMemo");

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> delFl = createNumber("delFl", Integer.class);

    public final NumberPath<Long> ireview = createNumber("ireview", Long.class);

    public final com.baby.babycareproductsshop.entity.order.QOrderEntity orderEntity;

    public final com.baby.babycareproductsshop.entity.product.QProductEntity productEntity;

    public final NumberPath<Integer> productScore = createNumber("productScore", Integer.class);

    public final StringPath reqReviewPic = createString("reqReviewPic");

    public final com.baby.babycareproductsshop.entity.user.QUserEntity userEntity;

    public QReviewEntity(String variable) {
        this(ReviewEntity.class, forVariable(variable), INITS);
    }

    public QReviewEntity(Path<? extends ReviewEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewEntity(PathMetadata metadata, PathInits inits) {
        this(ReviewEntity.class, metadata, inits);
    }

    public QReviewEntity(Class<? extends ReviewEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderEntity = inits.isInitialized("orderEntity") ? new com.baby.babycareproductsshop.entity.order.QOrderEntity(forProperty("orderEntity"), inits.get("orderEntity")) : null;
        this.productEntity = inits.isInitialized("productEntity") ? new com.baby.babycareproductsshop.entity.product.QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new com.baby.babycareproductsshop.entity.user.QUserEntity(forProperty("userEntity")) : null;
    }

}

