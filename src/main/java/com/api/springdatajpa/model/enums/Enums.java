package com.api.springdatajpa.model.enums;

public class Enums {
    public enum CustomerSortBy {
        customerId,
        customerName,
        address,
        phoneNumber,
    }

    public enum ProductSortBy {
        productId,
        productName,
        unitPrice,
        description,
    }

    public enum Status {
        PENDING,
        SHIPPED,
        DELIVERING,
        DELIVERED
    }
}
