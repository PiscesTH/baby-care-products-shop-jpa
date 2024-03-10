package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBannerEntity is a Querydsl query type for BannerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBannerEntity extends EntityPathBase<BannerEntity> {

    private static final long serialVersionUID = 1739502551L;

    public static final QBannerEntity bannerEntity = new QBannerEntity("bannerEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final StringPath bannerPic = createString("bannerPic");

    public final StringPath bannerUrl = createString("bannerUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> ibanner = createNumber("ibanner", Long.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Integer> target = createNumber("target", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBannerEntity(String variable) {
        super(BannerEntity.class, forVariable(variable));
    }

    public QBannerEntity(Path<? extends BannerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBannerEntity(PathMetadata metadata) {
        super(BannerEntity.class, metadata);
    }

}

