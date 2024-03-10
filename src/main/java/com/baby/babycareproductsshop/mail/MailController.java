package com.baby.babycareproductsshop.mail;

import com.baby.babycareproductsshop.mail.model.EmailMessageDto;
import com.baby.babycareproductsshop.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/mail")
@Tag(name = "메일 API", description = "메일 관련 파트")
public class MailController {
    private final ConsoleMailService consoleMailService;
    private final HtmlMailService htmlMailService;

    @Operation(summary = "메일 발송 기능 테스트(console-log)", description = """
            응답값으로 메일 기능 테스트
            """)
    @PostMapping("/console-test")
    public ApiResponse<?> consoleTest(@RequestBody EmailMessageDto dto) {
        consoleMailService.send(dto);
        return new ApiResponse<>(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), dto);
    }

    @Operation(summary = "회원 메일 발송 기능", description = """
            실제 메일 발송 기능
            """)
    @PostMapping
    public void sendTest(@RequestBody EmailMessageDto dto) {
        htmlMailService.send(dto);
    }
}
