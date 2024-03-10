package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.entity.board.BoardCommentEntity;
import com.baby.babycareproductsshop.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
    List<BoardCommentEntity> findAllByBoardEntity(BoardEntity entity);
    List<BoardCommentEntity> findAllByBoardEntityIn(List<BoardEntity> entityList);
}