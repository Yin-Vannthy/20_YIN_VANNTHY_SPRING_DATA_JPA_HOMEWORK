package com.api.springdatajpa.service.serviceImpl;


import com.api.springdatajpa.exception.CustomNotFoundException;
import com.api.springdatajpa.model.dto.CustomerDto;
import com.api.springdatajpa.model.entity.Customer;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.CustomerRequest;
import com.api.springdatajpa.repository.CustomerRepository;
import com.api.springdatajpa.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CustomerRequest customerRequest) {
        findCustomerByEmail(customerRequest.getEmail()).ifPresent(
                email -> {
                    throw new CustomNotFoundException("This email is already in used.");
                }
        );
        return customerRepository.save(customerRequest.toCustomerEntity()).toCustomerResponse();
    }

    @Override
    public Optional<CustomerDto> updateCustomerById(Long customerId, CustomerRequest customerRequest) {
        findCustomerByEmail(customerRequest.getEmail()).ifPresent(c -> {
            if (!c.getCustomerId().equals(customerId)) {
                throw new CustomNotFoundException("This email is already in use by another user.");
            }
        });

        return Optional.ofNullable(customerRepository.findById(customerId)
                .map(c -> {
                    c.toCustomerEntity(customerRequest);
                    return customerRepository.save(c).toCustomerWithOrderResponse();
                }).orElseThrow(
                        () -> new CustomNotFoundException("No customer with Id : " + customerId + " found")
                ));
    }


    @Override
    public Optional<CustomerDto> findCustomerById(Long customerId) {
        return Optional.of(
                customerRepository.findById(customerId).orElseThrow(
                        () -> new CustomNotFoundException("No customer with Id : " + customerId + " found")
                ).toCustomerWithOrderResponse());
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        findCustomerById(customerId).ifPresent(customer -> customerRepository.deleteById(customer.getCustomerId()));
    }

    @Override
    public List<CustomerDto> findAllCustomer(Integer pageNo, Integer pageSize, Enums.CustomerSortBy sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy.name());
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return customerRepository.findAll(pageable).getContent()
                .stream().map(Customer::toCustomerWithOrderResponse).toList();
    }

    public Optional<CustomerDto> findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email.toLowerCase()).map(Customer::toCustomerWithOrderResponse);
    }
}
