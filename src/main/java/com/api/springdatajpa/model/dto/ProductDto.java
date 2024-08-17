package com.api.springdatajpa.model.dto;

import com.api.springdatajpa.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String productName;
    private Float unitPrice;
    private String description;

    public Product toProductEntity() {
        return new Product(this.id, this.productName.toUpperCase(), this.unitPrice, this.description);
    }


}
