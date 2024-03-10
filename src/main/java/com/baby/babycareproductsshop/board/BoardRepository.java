package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.entity.board.BoardCommentEntity;
import com.baby.babycareproductsshop.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAllByBoardCode(long boardCode);
    List<BoardEntity> findAllByBoardCodeAndTitleContainsOrContentsContains(long boardCode, String title, String contents);
}
