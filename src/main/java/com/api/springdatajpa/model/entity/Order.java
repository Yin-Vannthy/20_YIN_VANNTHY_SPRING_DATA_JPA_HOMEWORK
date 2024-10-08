package com.api.springdatajpa.model.entity;

import com.api.springdatajpa.model.dto.OrderDto;
import com.api.springdatajpa.model.dto.ProductDto;
import com.api.springdatajpa.model.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "order_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    private String orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    @Column(name = "total_amount")
    private Float totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Enums.Status status = Enums.Status.PENDING;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductOrder> productOrder;

    public OrderDto toOrderResponse(List<ProductDto> products) {
        return new OrderDto(this.orderId, this.orderDate, this.totalAmount, this.status , products);
    }

    public Order (Customer customer) {
        this.customer = customer;
    }

    public OrderDto toOrderResponse() {
        return new OrderDto(
                this.orderId,
                this.orderDate,
                this.totalAmount,
                this.status,
                this.productOrder.stream().map(pro -> pro.getProduct().toProductResponse()).toList());
    }
}
