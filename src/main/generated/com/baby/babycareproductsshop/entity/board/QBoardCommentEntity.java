package com.baby.babycareproductsshop.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardCommentEntity is a Querydsl query type for BoardCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardCommentEntity extends EntityPathBase<BoardCommentEntity> {

    private static final long serialVersionUID = -619510675L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardCommentEntity boardCommentEntity = new QBoardCommentEntity("boardCommentEntity");

    public final com.baby.babycareproductsshop.entity.QBaseEntity _super = new com.baby.babycareproductsshop.entity.QBaseEntity(this);

    public final QBoardEntity boardEntity;

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> icomment = createNumber("icomment", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.baby.babycareproductsshop.entity.user.QUserEntity userEntity;

    public QBoardCommentEntity(String variable) {
        this(BoardCommentEntity.class, forVariable(variable), INITS);
    }

    public QBoardCommentEntity(Path<? extends BoardCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardCommentEntity(PathMetadata metadata, PathInits inits) {
        this(BoardCommentEntity.class, metadata, inits);
    }

    public QBoardCommentEntity(Class<? extends BoardCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardEntity = inits.isInitialized("boardEntity") ? new QBoardEntity(forProperty("boardEntity"), inits.get("boardEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new com.baby.babycareproductsshop.entity.user.QUserEntity(forProperty("userEntity")) : null;
    }

}

