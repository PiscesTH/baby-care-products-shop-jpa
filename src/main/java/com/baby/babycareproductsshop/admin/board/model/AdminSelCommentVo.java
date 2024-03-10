package com.baby.babycareproductsshop.admin.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminSelCommentVo {
    @Schema(title = "유저 pk")
    private long iuser;
    @Schema(title = "댓글 pk")
    private long icomment;
    @Schema(title = "댓글 내용")
    private String comment;
}
