package com.api.springdatajpa.model.dto;

import com.api.springdatajpa.model.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private Float totalAmount;
    private Enums.Status status;
    private List<ProductDto> productOrder;
}
