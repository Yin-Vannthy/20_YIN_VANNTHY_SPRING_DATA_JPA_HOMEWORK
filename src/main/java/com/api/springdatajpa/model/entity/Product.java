package com.api.springdatajpa.model.entity;

import com.api.springdatajpa.model.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(name = "unit_price", nullable = false, precision = 10)
    private Float unitPrice;

    @Column(length = 100)
    private String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductOrder> productOrder;

    public Product(Long productId, String productName, Float unitPrice, String description) {
        this.productId = productId;
        this.productName = productName.toUpperCase();
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public ProductDto toProductResponse() {
        return new ProductDto(this.productId, this.productName, this.unitPrice, this.description);
    }
}
