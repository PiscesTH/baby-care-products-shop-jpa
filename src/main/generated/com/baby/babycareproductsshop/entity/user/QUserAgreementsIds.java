package com.baby.babycareproductsshop.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserAgreementsIds is a Querydsl query type for UserAgreementsIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserAgreementsIds extends BeanPath<UserAgreementsIds> {

    private static final long serialVersionUID = 180234602L;

    public static final QUserAgreementsIds userAgreementsIds = new QUserAgreementsIds("userAgreementsIds");

    public final NumberPath<Long> iclause = createNumber("iclause", Long.class);

    public final NumberPath<Long> iuser = createNumber("iuser", Long.class);

    public QUserAgreementsIds(String variable) {
        super(UserAgreementsIds.class, forVariable(variable));
    }

    public QUserAgreementsIds(Path<? extends UserAgreementsIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAgreementsIds(PathMetadata metadata) {
        super(UserAgreementsIds.class, metadata);
    }

}

