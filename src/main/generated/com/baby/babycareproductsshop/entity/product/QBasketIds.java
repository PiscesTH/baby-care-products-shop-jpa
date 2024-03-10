package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasketIds is a Querydsl query type for BasketIds
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBasketIds extends BeanPath<BasketIds> {

    private static final long serialVersionUID = -939429270L;

    public static final QBasketIds basketIds = new QBasketIds("basketIds");

    public final NumberPath<Long> iproduct = createNumber("iproduct", Long.class);

    public final NumberPath<Long> iuser = createNumber("iuser", Long.class);

    public QBasketIds(String variable) {
        super(BasketIds.class, forVariable(variable));
    }

    public QBasketIds(Path<? extends BasketIds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasketIds(PathMetadata metadata) {
        super(BasketIds.class, metadata);
    }

}

