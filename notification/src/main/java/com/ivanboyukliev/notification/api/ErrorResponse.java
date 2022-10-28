package com.ivanboyukliev.notification.api;

import lombok.Data;

@Data
public class ErrorResponse {
  private String message;
  private Integer code;

  public ErrorResponse(String message, Integer code) {
    this.message = message;
    this.code = code;
  }
}
