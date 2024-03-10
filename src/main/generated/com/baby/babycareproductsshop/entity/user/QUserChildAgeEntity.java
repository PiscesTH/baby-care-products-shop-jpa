package com.baby.babycareproductsshop.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserChildAgeEntity is a Querydsl query type for UserChildAgeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserChildAgeEntity extends EntityPathBase<UserChildAgeEntity> {

    private static final long serialVersionUID = -8465333L;

    public static final QUserChildAgeEntity userChildAgeEntity = new QUserChildAgeEntity("userChildAgeEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    public final StringPath childAge = createString("childAge");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> ichildAge = createNumber("ichildAge", Long.class);

    public QUserChildAgeEntity(String variable) {
        super(UserChildAgeEntity.class, forVariable(variable));
    }

    public QUserChildAgeEntity(Path<? extends UserChildAgeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserChildAgeEntity(PathMetadata metadata) {
        super(UserChildAgeEntity.class, metadata);
    }

}

