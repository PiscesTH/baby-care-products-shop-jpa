package com.baby.babycareproductsshop.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserSignupClauseEntity is a Querydsl query type for UserSignupClauseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSignupClauseEntity extends EntityPathBase<UserSignupClauseEntity> {

    private static final long serialVersionUID = 1263788335L;

    public static final QUserSignupClauseEntity userSignupClauseEntity = new QUserSignupClauseEntity("userSignupClauseEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> iclause = createNumber("iclause", Long.class);

    public final StringPath required = createString("required");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUserSignupClauseEntity(String variable) {
        super(UserSignupClauseEntity.class, forVariable(variable));
    }

    public QUserSignupClauseEntity(Path<? extends UserSignupClauseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserSignupClauseEntity(PathMetadata metadata) {
        super(UserSignupClauseEntity.class, metadata);
    }

}

