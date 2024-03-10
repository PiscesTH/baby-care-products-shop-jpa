package com.baby.babycareproductsshop.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1949149912L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final ListPath<UserAddressEntity, QUserAddressEntity> addressEntityList = this.<UserAddressEntity, QUserAddressEntity>createList("addressEntityList", UserAddressEntity.class, QUserAddressEntity.class, PathInits.DIRECT2);

    public final StringPath adminMemo = createString("adminMemo");

    public final ListPath<UserChildEntity, QUserChildEntity> childEntityList = this.<UserChildEntity, QUserChildEntity>createList("childEntityList", UserChildEntity.class, QUserChildEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> iuser = createNumber("iuser", Long.class);

    public final StringPath nm = createString("nm");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<com.baby.babycareproductsshop.common.ProviderTypeEnum> providerType = createEnum("providerType", com.baby.babycareproductsshop.common.ProviderTypeEnum.class);

    public final EnumPath<com.baby.babycareproductsshop.common.RoleEnum> role = createEnum("role", com.baby.babycareproductsshop.common.RoleEnum.class);

    public final StringPath uid = createString("uid");

    public final NumberPath<Long> unregisterFl = createNumber("unregisterFl", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath upw = createString("upw");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

