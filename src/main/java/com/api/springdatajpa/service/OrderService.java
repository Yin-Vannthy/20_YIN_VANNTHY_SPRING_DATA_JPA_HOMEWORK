package com.api.springdatajpa.service;

import com.api.springdatajpa.model.dto.OrderDto;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.OrderRequest;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto createOrder(Long customerId, List<OrderRequest> orderRequests);

    OrderDto updateOrderStatusByOrderId(Long orderId, Enums.Status status);

    Optional<OrderDto> findOrderById(Long orderId);

    List<OrderDto> findOrderByCustomerId(Long customerId);
}
