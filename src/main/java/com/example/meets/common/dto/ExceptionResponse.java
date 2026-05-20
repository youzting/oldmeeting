package com.example.meets.common.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {

  private final int errorCode;
  private final String message;
  private final String path;
  private final LocalDateTime time;

  public static ExceptionResponse from(int errorCode, String message, String path) {
    return ExceptionResponse.builder()
        .errorCode(errorCode)
        .message(message)
        .path(path)
        .time(LocalDateTime.now())
        .build();
  }
}

