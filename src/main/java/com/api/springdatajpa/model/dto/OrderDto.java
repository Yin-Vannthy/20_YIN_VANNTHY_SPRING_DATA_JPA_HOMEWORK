package com.api.springdatajpa.model.dto;

import com.api.springdatajpa.model.enums.Enums;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String orderDate;
    private Float totalAmount;
    private Enums.Status status;
    private List<ProductDto> productOrder;
}
