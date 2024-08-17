package com.api.springdatajpa.model.request;

import com.api.springdatajpa.model.entity.Product;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductRequest {
    @NotEmpty(message = "Address Can not be empty")
    @NotBlank(message = "Address Can not be blank")
    @NotNull(message = "Address Can not be null")
    private String productName;

    @NotNull(message = "Unit price cannot be null")
    @Positive(message = "Unit price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid unit price format. Maximum 10 integer digits and 2 decimal digits allowed.")
    private Float unitPrice;

    @NotEmpty(message = "Description Can not be empty")
    @NotBlank(message = "Description Can not be blank")
    @NotNull(message = "Description Can not be null")
    private String description;

    public Product toProductEntity() {
        return new Product(null, this.productName.toUpperCase(), this.unitPrice, this.description);
    }

    public Product toProductEntity(Long productId) {
        return new Product(productId, this.productName.toUpperCase(), this.unitPrice, this.description);
    }
}
