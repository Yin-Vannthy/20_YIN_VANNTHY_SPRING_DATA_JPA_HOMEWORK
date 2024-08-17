package com.api.springdatajpa.service;

import com.api.springdatajpa.model.dto.ProductDto;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.ProductRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDto> createProduct(ProductRequest productRequest);

    Optional<ProductDto> findProductById(Long productId);

    void deleteProductById(Long productId);

    List<ProductDto> findAllProducts(Integer pageNo, Integer pageSize, Enums.ProductSortBy sortBy, Sort.Direction sortDirection);

    Optional<ProductDto> updateProductById(Long productId, ProductRequest productRequest);

    List<ProductDto> findProductByOrderId(Long orderId);
}
