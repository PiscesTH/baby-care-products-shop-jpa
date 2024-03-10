package com.baby.babycareproductsshop.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserChildEntity is a Querydsl query type for UserChildEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserChildEntity extends EntityPathBase<UserChildEntity> {

    private static final long serialVersionUID = -1410199046L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserChildEntity userChildEntity = new QUserChildEntity("userChildEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> ichild = createNumber("ichild", Long.class);

    public final QUserChildAgeEntity userChildAgeEntity;

    public final QUserEntity userEntity;

    public QUserChildEntity(String variable) {
        this(UserChildEntity.class, forVariable(variable), INITS);
    }

    public QUserChildEntity(Path<? extends UserChildEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserChildEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserChildEntity(PathMetadata metadata, PathInits inits) {
        this(UserChildEntity.class, metadata, inits);
    }

    public QUserChildEntity(Class<? extends UserChildEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userChildAgeEntity = inits.isInitialized("userChildAgeEntity") ? new QUserChildAgeEntity(forProperty("userChildAgeEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

