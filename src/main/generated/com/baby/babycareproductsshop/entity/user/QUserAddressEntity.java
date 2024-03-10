package com.baby.babycareproductsshop.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAddressEntity is a Querydsl query type for UserAddressEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAddressEntity extends EntityPathBase<UserAddressEntity> {

    private static final long serialVersionUID = 1148595218L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAddressEntity userAddressEntity = new QUserAddressEntity("userAddressEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> iaddress = createNumber("iaddress", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUserEntity userEntity;

    public final StringPath zipCode = createString("zipCode");

    public QUserAddressEntity(String variable) {
        this(UserAddressEntity.class, forVariable(variable), INITS);
    }

    public QUserAddressEntity(Path<? extends UserAddressEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAddressEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAddressEntity(PathMetadata metadata, PathInits inits) {
        this(UserAddressEntity.class, metadata, inits);
    }

    public QUserAddressEntity(Class<? extends UserAddressEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

