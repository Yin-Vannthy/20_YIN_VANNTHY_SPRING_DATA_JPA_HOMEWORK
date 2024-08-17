package com.api.springdatajpa.model.dto;

import com.api.springdatajpa.model.entity.Customer;
import com.api.springdatajpa.model.entity.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CustomerDto {
    private Long customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Email email;
    private List<OrderDto> orderList = new ArrayList<>();

    public Customer toCustomerEntity() {
        return new Customer(
                this.customerId,
                this.customerName,
                this.address,
                this.phoneNumber,
                this.email);
    }

    public CustomerDto(Long customerId, String customerName, String address, String phoneNumber, Email email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
