package com.baby.babycareproductsshop.mail.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class EmailMessageDto {
    @Schema(description = "수신인 메일 주소")
    private List<String> to;
    @Schema(description = "메일 제목")
    private String subject;
    @Schema(description = "메일 내용")
    private String message;
}
