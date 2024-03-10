package com.baby.babycareproductsshop.admin.board.model;

import com.baby.babycareproductsshop.entity.board.BoardCommentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class AdminSelBoardVo {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "게시글 작성자 이름")
    private String writerNm;
    @Schema(title = "게시판 pk")
    private long iboard;
    @Schema(title = "게시글 제목")
    private String title;
    @Schema(title = "게시글 내용")
    private String contents;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "답변 여부")
    private Integer responseFl;
    @Builder.Default
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "게시글 댓글")
    private List<AdminSelCommentVo> commentList = new ArrayList<>();
}
