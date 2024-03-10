package com.baby.babycareproductsshop.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAgreementsEntity is a Querydsl query type for UserAgreementsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAgreementsEntity extends EntityPathBase<UserAgreementsEntity> {

    private static final long serialVersionUID = 554761201L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAgreementsEntity userAgreementsEntity = new QUserAgreementsEntity("userAgreementsEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    public final StringPath agreement = createString("agreement");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QUserAgreementsIds userAgreementsIds;

    public final QUserEntity userEntity;

    public final QUserSignupClauseEntity userSignupClauseEntity;

    public QUserAgreementsEntity(String variable) {
        this(UserAgreementsEntity.class, forVariable(variable), INITS);
    }

    public QUserAgreementsEntity(Path<? extends UserAgreementsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAgreementsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAgreementsEntity(PathMetadata metadata, PathInits inits) {
        this(UserAgreementsEntity.class, metadata, inits);
    }

    public QUserAgreementsEntity(Class<? extends UserAgreementsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userAgreementsIds = inits.isInitialized("userAgreementsIds") ? new QUserAgreementsIds(forProperty("userAgreementsIds")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
        this.userSignupClauseEntity = inits.isInitialized("userSignupClauseEntity") ? new QUserSignupClauseEntity(forProperty("userSignupClauseEntity")) : null;
    }

}

