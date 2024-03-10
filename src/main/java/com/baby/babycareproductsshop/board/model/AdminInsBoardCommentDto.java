package com.baby.babycareproductsshop.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AdminInsBoardCommentDto {
    @JsonIgnore
    private long iboard;
    private String comment;
}
