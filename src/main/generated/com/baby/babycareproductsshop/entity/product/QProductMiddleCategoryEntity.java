package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductMiddleCategoryEntity is a Querydsl query type for ProductMiddleCategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductMiddleCategoryEntity extends EntityPathBase<ProductMiddleCategoryEntity> {

    private static final long serialVersionUID = 1791872093L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductMiddleCategoryEntity productMiddleCategoryEntity = new QProductMiddleCategoryEntity("productMiddleCategoryEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    public final NumberPath<Long> candidateKey = createNumber("candidateKey", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> imiddle = createNumber("imiddle", Long.class);

    public final StringPath middleCategory = createString("middleCategory");

    public final QProductMainCategoryEntity productMainCategory;

    public QProductMiddleCategoryEntity(String variable) {
        this(ProductMiddleCategoryEntity.class, forVariable(variable), INITS);
    }

    public QProductMiddleCategoryEntity(Path<? extends ProductMiddleCategoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductMiddleCategoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductMiddleCategoryEntity(PathMetadata metadata, PathInits inits) {
        this(ProductMiddleCategoryEntity.class, metadata, inits);
    }

    public QProductMiddleCategoryEntity(Class<? extends ProductMiddleCategoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productMainCategory = inits.isInitialized("productMainCategory") ? new QProductMainCategoryEntity(forProperty("productMainCategory")) : null;
    }

}

