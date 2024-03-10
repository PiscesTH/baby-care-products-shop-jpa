package com.baby.babycareproductsshop.admin.board;

import com.baby.babycareproductsshop.admin.board.model.AdminSelBoardVo;
import com.baby.babycareproductsshop.board.model.AdminInsBoardCommentDto;
import com.baby.babycareproductsshop.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/board")
@Tag(name = "관리자 1대1 문의 API", description = "관리자 1대1 문의 관련 파트")
public class AdminBoardController {
    private final AdminBoardService service;

    @Operation(summary = "1대1 문의 리스트 조회", description = """
            keyword : 검색어
            """)
    @GetMapping
    public ApiResponse<List<AdminSelBoardVo>> getInquiryList(@RequestParam(required = false) String keyword) {
        return service.getInquiryList(keyword);
    }

    @Operation(summary = "1대1 문의 조회")
    @GetMapping("/{iboard}")
    public ApiResponse<AdminSelBoardVo> getInquiry(@PathVariable long iboard) {
        return service.getInquiry(iboard);
    }

    @Operation(summary = "1대1 문의 답변 작성")
    @PostMapping("/{iboard}")
    public ApiResponse<?> postBoardComment(@PathVariable long iboard,
                                           @RequestBody AdminInsBoardCommentDto dto) {
        dto.setIboard(iboard);
        return service.postBoardComment(dto);
    }

    @Operation(summary = "1대1 문의 삭제")
    @DeleteMapping
    public ApiResponse<?> delBoard(long iboard) {
        return service.delBoard(iboard);
    }

    @Operation(summary = "1대1 문의 답변 삭제")
    @DeleteMapping("/{iboard}")
    public ApiResponse<?> delBoardComment(@PathVariable long iboard, @RequestParam long icomment) {
        return service.delBoardComment(iboard, icomment);
    }
}
