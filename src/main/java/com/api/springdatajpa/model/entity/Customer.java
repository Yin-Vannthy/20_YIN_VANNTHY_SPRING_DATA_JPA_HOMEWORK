package com.api.springdatajpa.model.entity;

import com.api.springdatajpa.model.dto.CustomerDto;
import com.api.springdatajpa.model.dto.OrderDto;
import com.api.springdatajpa.model.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<Order> orders;

    public Customer(Long customerId, String customerName, String address, String phoneNumber, Email email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerDto toCustomerResponse(){
        return new CustomerDto(this.customerId, this.customerName, this.address, this.phoneNumber, this.email);
    }
}
