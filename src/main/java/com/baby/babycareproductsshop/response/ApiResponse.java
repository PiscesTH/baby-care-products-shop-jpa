package com.baby.babycareproductsshop.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    @Schema(title = "응답 코드")
    private final String code;
    @Schema(title = "코드 메세지")
    private final String message;
    @Schema(title = "응답 데이터")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final T data;

    public ApiResponse(String code, String message) {
        this(code, message, null);
    }

    public ApiResponse(T data) {
        this.code = "200";
        this.message = "OK";
        this.data = data;
    }
}
