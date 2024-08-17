package com.api.springdatajpa.service;

import com.api.springdatajpa.model.dto.CustomerDto;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.CustomerRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerDto createCustomer(CustomerRequest customerRequest);

    Optional<CustomerDto> updateCustomerById(Long customerId, CustomerRequest customerRequest);

    Optional<CustomerDto> findCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);

    List<CustomerDto> findAllCustomer(Integer pageNo, Integer pageSize, Enums.CustomerSortBy sortBy, Sort.Direction sortDirection);
}
