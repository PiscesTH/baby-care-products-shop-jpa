package com.baby.babycareproductsshop.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserClauseVo {
    @Schema(title = "약관 pk")
    private int iclause;
    @Schema(title = "약관 제목")
    private String title;
    @Schema(title = "약관 내용")
    private String contents;
    @Schema(title = "약관 필수 여부")
    private String required;
}
