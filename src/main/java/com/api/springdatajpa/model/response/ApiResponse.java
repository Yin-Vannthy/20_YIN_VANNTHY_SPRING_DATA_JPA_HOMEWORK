package com.api.springdatajpa.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse <T>{
    private String message;
    private HttpStatus status;
    private Integer code;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime localDateTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
}
