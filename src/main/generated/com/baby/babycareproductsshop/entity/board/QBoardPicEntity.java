package com.baby.babycareproductsshop.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardPicEntity is a Querydsl query type for BoardPicEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardPicEntity extends EntityPathBase<BoardPicEntity> {

    private static final long serialVersionUID = 1188223192L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardPicEntity boardPicEntity = new QBoardPicEntity("boardPicEntity");

    public final QBoardEntity boardEntity;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath pictureUrl = createString("pictureUrl");

    public QBoardPicEntity(String variable) {
        this(BoardPicEntity.class, forVariable(variable), INITS);
    }

    public QBoardPicEntity(Path<? extends BoardPicEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardPicEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardPicEntity(PathMetadata metadata, PathInits inits) {
        this(BoardPicEntity.class, metadata, inits);
    }

    public QBoardPicEntity(Class<? extends BoardPicEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardEntity = inits.isInitialized("boardEntity") ? new QBoardEntity(forProperty("boardEntity"), inits.get("boardEntity")) : null;
    }

}

