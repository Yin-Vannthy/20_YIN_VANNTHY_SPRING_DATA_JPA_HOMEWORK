package com.api.springdatajpa.controller;

import com.api.springdatajpa.model.dto.CustomerDto;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.CustomerRequest;
import com.api.springdatajpa.model.response.ApiResponse;
import com.api.springdatajpa.service.CustomerService;
import com.api.springdatajpa.util.APIResponseUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<ApiResponse<CustomerDto>> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        customerService.createCustomer(customerRequest),
                        HttpStatus.CREATED)
        );
    }

    @GetMapping("/findCustomerById/{customerId}")
    public ResponseEntity<ApiResponse<Optional<CustomerDto>>> findCustomerById(
            @PathVariable  Long customerId
    ) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        customerService.findCustomerById(customerId),
                        HttpStatus.OK
                )
        );
    }

    @PutMapping("/updateCustomerById/{customerId}")
    public ResponseEntity<ApiResponse<Optional<CustomerDto>>> updateCustomer(
            @PathVariable  Long customerId,
            @Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        customerService.updateCustomerById(customerId, customerRequest),
                        HttpStatus.OK
                )
        );
    }

    @DeleteMapping("/deleteCustomerById/{customerId}")
    public ResponseEntity<ApiResponse<?>> deleteCustomerById(@PathVariable   Long customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                       null,
                        HttpStatus.OK
                )
        );
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<ApiResponse<List<CustomerDto>>> getAllCustomer(
            @RequestParam(defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "customerName", required = false) Enums.CustomerSortBy sortBy,
            @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortDirection
    ) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        customerService.findAllCustomer(pageNo, pageSize, sortBy, sortDirection),
                        HttpStatus.OK
                )
        );
    }
}
