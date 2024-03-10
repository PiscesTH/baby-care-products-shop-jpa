package com.baby.babycareproductsshop.exception;

import com.baby.babycareproductsshop.common.Const;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    // 리뷰
    NOT_ALLOWED_PICS_SIZE(HttpStatus.NOT_FOUND, "사진은 최대 5장까지만 넣을 수 있습니다."),
    DEL_REVIEW_NOT_FAIL(HttpStatus.NOT_FOUND, "리뷰가 삭제되지 않았습니다."),
    REVIEW_NOT_PRODUCT_SCORE_OR_CONTENTS_PIC_OVER_REVIEW(HttpStatus.BAD_REQUEST, "사용하신 제품의 별점과 사용 후기를 알려주세요."),
    UPLOAD_REVIEW_REGISTRATION_REVIEW(HttpStatus.OK, "리뷰가 정상적으로 등록되었습니다."),

    // 게시판 / 댓글
    GLOBAL_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 사항을 처리할 수 없습니다."),
    PICS_CREATE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "사진 등록에 실패했습니다."),
    USER_MODIFY_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "작성자 외 수정 및 삭제가 불가능합니다."),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."),
    POST_REGISTER_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "게시글 등록 및 수정이 불가능합니다."),
    POST_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "게시글을 삭제할 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."),
    COMMENT_REGISTER_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 등록 및 수정이 불가능합니다."),
    COMMENT_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 수정이 불가능합니다."),
    IMAGE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "이미지 업로드에 실패했습니다."),

    // 회원
    INVALID_ADDRESS_SIZE(HttpStatus.BAD_REQUEST, "주소는 최소 1개 최대 3개 까지 저장할 수 있습니다."),
    ADDRESS_UPDATE_FAIL(HttpStatus.NOT_FOUND, "주소 수정에 실패했습니다."),
    DUPLICATED_UID(HttpStatus.BAD_REQUEST, "이미 사용중인 아이디입니다."),
    PASSWORD_NOT_MATCHED(HttpStatus.NOT_FOUND, "비밀번호를 확인해주세요."),
    LOGIN_FAIL(HttpStatus.NOT_FOUND, "아이디와 비밀번호를 확인해주세요."),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND, "refresh-token 이 없습니다."),
    UNREGISTER_USER(HttpStatus.BAD_REQUEST, "탈퇴한 회원입니다."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "관리자 권한이 없습니다."),

    // 상품
    NOT_FOUND_ORDER_LIST(HttpStatus.NOT_FOUND, "해당하는 상품 내역이 없습니다."),
    NOT_FOUND_PRODUCT_PICS(HttpStatus.BAD_REQUEST, "상품 사진을 선택해주세요."),
    PRODUCT_NM_IS_BLANK(HttpStatus.NOT_FOUND, "상품 이름을 입력해주세요"),
    PRODUCT_PRICE_IS_ZERO(HttpStatus.NOT_FOUND, "상품 가격을 입력해주세요"),

    // 관리자 페이지 - 주문
    SEARCH_FAILED_ERROR(HttpStatus.NOT_FOUND, "검색어 타입이 올바르지 않습니다."),
    PROCESS_STATE_CODE_ERROR(HttpStatus.NOT_FOUND, "주문 처리 상태 코드가 올바르지 않습니다."),
    PROCESS_STATE_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 주문 처리 코드를 찾을 수 없습니다."),
    ORDER_BATCH_PROCESS_FAIL(HttpStatus.NOT_FOUND, "주문 일괄 처리에 실패했습니다."),

    CATEGORY_INSERT_FAIL(HttpStatus.NOT_FOUND, "카테고리 등록에 실패했습니다."),
    CATEGORY_UPDATE_FAIL(HttpStatus.NOT_FOUND, "카테고리 수정에 실패했습니다."),
    CATEGORY_DELETE_FAIL(HttpStatus.NOT_FOUND, "카테고리 삭제에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
