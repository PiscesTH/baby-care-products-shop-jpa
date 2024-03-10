package com.baby.babycareproductsshop.entity.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewPicEntity is a Querydsl query type for ReviewPicEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewPicEntity extends EntityPathBase<ReviewPicEntity> {

    private static final long serialVersionUID = -2072810616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewPicEntity reviewPicEntity = new QReviewPicEntity("reviewPicEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReviewEntity review;

    public final StringPath reviewPictureUrl = createString("reviewPictureUrl");

    public QReviewPicEntity(String variable) {
        this(ReviewPicEntity.class, forVariable(variable), INITS);
    }

    public QReviewPicEntity(Path<? extends ReviewPicEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewPicEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewPicEntity(PathMetadata metadata, PathInits inits) {
        this(ReviewPicEntity.class, metadata, inits);
    }

    public QReviewPicEntity(Class<? extends ReviewPicEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReviewEntity(forProperty("review"), inits.get("review")) : null;
    }

}

