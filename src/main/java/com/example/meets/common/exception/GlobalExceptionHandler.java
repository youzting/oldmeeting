package com.example.meets.common.exception;

import com.example.meets.common.dto.ApiResponse;
import com.example.meets.common.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<ErrorResponse>> handleValidationException(
      MethodArgumentNotValidException ex) {
    FieldError fieldError =
        ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
    String message =
        fieldError == null
            ? ErrorCode.VALIDATION_ERROR.getMessage()
            : fieldError.getDefaultMessage();

    ErrorResponse body = new ErrorResponse(ErrorCode.VALIDATION_ERROR.name(), message);
    return ResponseEntity.status(ErrorCode.VALIDATION_ERROR.getStatus())
        .body(ApiResponse.fail(ErrorCode.VALIDATION_ERROR.getStatus(), body));
  }

  @ExceptionHandler({ConstraintViolationException.class, HandlerMethodValidationException.class})
  public ResponseEntity<ApiResponse<ErrorResponse>> handleMethodValidationException(Exception ex) {
    String message =
        ex instanceof ConstraintViolationException violationException
            ? violationException.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse(ErrorCode.VALIDATION_ERROR.getMessage())
            : ErrorCode.VALIDATION_ERROR.getMessage();

    ErrorResponse body = new ErrorResponse(ErrorCode.VALIDATION_ERROR.name(), message);
    return ResponseEntity.status(ErrorCode.VALIDATION_ERROR.getStatus())
        .body(ApiResponse.fail(ErrorCode.VALIDATION_ERROR.getStatus(), body));
  }

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ExceptionResponse> handleServiceException(
      ServiceException e, HttpServletRequest request) {
    return ResponseEntity.status(e.getStatus())
        .body(
            ExceptionResponse.from(e.getStatus().value(), e.getMessage(), request.getRequestURI()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(
      IllegalArgumentException e, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            ExceptionResponse.from(
                HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleException(
      Exception e, HttpServletRequest request) {
    log.error(
        "Unhandled Exception - uri: {}, message: {}", request.getRequestURI(), e.getMessage(), e);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ExceptionResponse.from(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                request.getRequestURI()));
  }
}

