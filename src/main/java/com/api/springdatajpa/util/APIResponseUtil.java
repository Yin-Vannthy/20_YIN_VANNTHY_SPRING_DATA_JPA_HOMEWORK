package com.api.springdatajpa.util;

import com.api.springdatajpa.response.ApiResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class APIResponseUtil {
    public static <T> ApiResponse<T> apiResponse(T payload, HttpStatus status) {
        return ApiResponse.<T>builder()
                .message("Successfully")
                .payload(payload)
                .status(status)
                .code(status.value())
                .localDateTime(LocalDateTime.now())
                .build();
    }
}
