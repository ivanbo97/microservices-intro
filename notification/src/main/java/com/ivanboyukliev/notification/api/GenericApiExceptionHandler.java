package com.ivanboyukliev.notification.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class GenericApiExceptionHandler {

  @ExceptionHandler({EntityNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse handleEntityNotFound(Exception ex) {
    log.error(ex.getMessage(), ex);
    return new ErrorResponse(ex.getMessage(), null);
  }
}
