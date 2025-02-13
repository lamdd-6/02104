package com.java._4.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private String errorCode;
    private Instant timestamp;
    private HttpStatus status;

    // Success response
    public static <T> ApiResponse success(T data, String message) {
        return new ApiResponse<>(data, message, null, Instant.now(), HttpStatus.OK);
    }

    // Error response
    public static <T> ApiResponse error(String message, String errorCode, HttpStatus status) {
        return new ApiResponse<>(null, message, errorCode, Instant.now(), status);
    }
}
