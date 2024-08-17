package com.api.springdatajpa.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

    @NotNull(message = "Quantity Can not be null")
    private Integer quantity;

    @NotNull(message = "Product Id Can not be null")
    private Long productId;
}
