package com.api.springdatajpa.controller;

import com.api.springdatajpa.model.dto.OrderDto;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.OrderRequest;
import com.api.springdatajpa.model.response.ApiResponse;
import com.api.springdatajpa.service.OrderService;
import com.api.springdatajpa.util.APIResponseUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/createOrder/{customerId}")
    public ResponseEntity<ApiResponse<OrderDto>> createOrder(@PathVariable Long customerId, @RequestBody @Valid List<OrderRequest> orderRequestList) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        orderService.createOrder(customerId, orderRequestList),
                        HttpStatus.CREATED
                )
        );
    }

    @GetMapping("/findOrderById/{orderId}")
    public ResponseEntity<ApiResponse<Optional<OrderDto>>> findOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        orderService.findOrderById(orderId),
                        HttpStatus.OK
                )
        );
    }

    @PutMapping("/updateOrderStatus/{orderId}")
    public ResponseEntity<ApiResponse<OrderDto>> updateOrderStatusByOrderId(
            @RequestParam(defaultValue = "PENDING", required = false) Enums.Status status,
            @PathVariable Long orderId) {
        return ResponseEntity.ok(
          APIResponseUtil.apiResponse(
                  orderService.updateOrderStatusByOrderId(orderId, status),
                  HttpStatus.OK
          )
        );
    }

    @GetMapping("/findOrderByCustomerId/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderDto>>> findOrderByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        orderService.findOrderByCustomerId(customerId),
                        HttpStatus.OK
                )
        );
    }
}
