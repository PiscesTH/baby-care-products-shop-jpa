package com.baby.babycareproductsshop.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductMainCategoryEntity is a Querydsl query type for ProductMainCategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductMainCategoryEntity extends EntityPathBase<ProductMainCategoryEntity> {

    private static final long serialVersionUID = -1698760895L;

    public static final QProductMainCategoryEntity productMainCategoryEntity = new QProductMainCategoryEntity("productMainCategoryEntity");

    public final com.baby.babycareproductsshop.entity.QCreatedAtEntity _super = new com.baby.babycareproductsshop.entity.QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> imain = createNumber("imain", Long.class);

    public final StringPath mainCategory = createString("mainCategory");

    public QProductMainCategoryEntity(String variable) {
        super(ProductMainCategoryEntity.class, forVariable(variable));
    }

    public QProductMainCategoryEntity(Path<? extends ProductMainCategoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductMainCategoryEntity(PathMetadata metadata) {
        super(ProductMainCategoryEntity.class, metadata);
    }

}

