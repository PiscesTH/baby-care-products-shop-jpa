package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWishIds is a Querydsl query type for WishIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QWishIds extends BeanPath<WishIds> {

    private static final long serialVersionUID = 1640954345L;

    public static final QWishIds wishIds = new QWishIds("wishIds");

    public final NumberPath<Long> iproduct = createNumber("iproduct", Long.class);

    public final NumberPath<Long> iuser = createNumber("iuser", Long.class);

    public QWishIds(String variable) {
        super(WishIds.class, forVariable(variable));
    }

    public QWishIds(Path<? extends WishIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWishIds(PathMetadata metadata) {
        super(WishIds.class, metadata);
    }

}

