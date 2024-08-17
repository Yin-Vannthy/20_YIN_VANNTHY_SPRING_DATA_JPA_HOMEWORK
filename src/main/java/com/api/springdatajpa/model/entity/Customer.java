package com.api.springdatajpa.model.entity;

import com.api.springdatajpa.model.dto.CustomerDto;
import com.api.springdatajpa.model.request.CustomerRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(nullable = false, length = 30)
    private String customerName;

    @Column(nullable = false, length = 20)
    private String address;

    @Column(nullable = false, length = 12)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "email_id", referencedColumnName = "id")
    private Email email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer(Long customerId, String customerName, String address, String phoneNumber, Email email) {
        this.customerId = customerId;
        this.customerName = customerName.toUpperCase();
        this.address = address.toUpperCase();
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerDto toCustomerResponse(){
        return new CustomerDto(
                this.customerId,
                this.customerName.toUpperCase(),
                this.address.toUpperCase(),
                this.phoneNumber,
                this.email);
    }

    public CustomerDto toCustomerWithOrderResponse(){
        return new CustomerDto(
                this.customerId,
                this.customerName.toUpperCase(),
                this.address.toUpperCase(),
                this.phoneNumber,
                this.email,
                this.orders.stream().map(Order::toOrderResponse).toList());
    }

    public void toCustomerEntity(CustomerRequest customerRequest){
        this.customerName = customerRequest.getCustomerName().toUpperCase();
        this.address = customerRequest.getAddress().toUpperCase();
        this.phoneNumber = customerRequest.getPhoneNumber();
        this.email.setEmail(customerRequest.getEmail().toLowerCase());
    }
}
