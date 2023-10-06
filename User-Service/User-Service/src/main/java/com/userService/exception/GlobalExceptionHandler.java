package com.userService.exception;

import com.userService.payload.ApiRespoce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  public ResponseEntity<ApiRespoce> handlerResourceNotFoundException(ResourceNotFoundException ex){
      String message = ex.getMessage();
      ApiRespoce respoce = ApiRespoce.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
      return new ResponseEntity<>(respoce,HttpStatus.NOT_FOUND);
  }
}
