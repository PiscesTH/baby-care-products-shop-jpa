package com.baby.babycareproductsshop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        List<ErrorResponse.ValidError> errors = new ArrayList<>();
/*        long beforeTestTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(ErrorResponse.ValidError::putError)
                    .toList();
        }
        long streamMeasurementTime = System.currentTimeMillis() - beforeTestTime;
        log.info("streamMeasurementTime : {}", streamMeasurementTime);*/
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.add(ErrorResponse.ValidError.putError(fieldError));
        }
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER, errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIIllegalArgumentException(IllegalArgumentException e) {
        log.warn("handleIIllegalArgument", e);
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER, CommonErrorCode.INVALID_PARAMETER.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> NumberFormatException(NumberFormatException e) {
        log.warn("NumberFormatException", e);
        return handleExceptionInternal(AuthErrorCode.SEARCH_FAILED_ERROR, AuthErrorCode.SEARCH_FAILED_ERROR.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.warn("handleException", e);
        return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleRestApiException(RestApiException e) {
        log.warn("handleRestApiException", e);
        return handleExceptionInternal(e.getErrorCode());
    }

    public ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return handleExceptionInternal(errorCode, errorCode.getMessage());
    }

    public ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(message == null ?
                        makeErrorResponse(errorCode)
                        : makeErrorResponse(errorCode, message, null));
    }

    public ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, List<ErrorResponse.ValidError> errors) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(errors == null ?
                        makeErrorResponse(errorCode)
                        : makeErrorResponse(errorCode, errors));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return makeErrorResponse(errorCode, errorCode.getMessage(), null);
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message, List<ErrorResponse.ValidError> errors) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .validErrorList(errors)
                .build();
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, List<ErrorResponse.ValidError> errors) {
        return makeErrorResponse(errorCode, errorCode.getMessage(), errors);
    }
}
