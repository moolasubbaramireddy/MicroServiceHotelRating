package com.userService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiRespoce {
    private  String message;
    private boolean success;
    private HttpStatus status;
}
