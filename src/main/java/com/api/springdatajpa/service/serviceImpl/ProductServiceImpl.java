package com.api.springdatajpa.service.serviceImpl;

import com.api.springdatajpa.exception.CustomNotFoundException;
import com.api.springdatajpa.model.dto.ProductDto;
import com.api.springdatajpa.model.entity.Product;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.ProductRequest;
import com.api.springdatajpa.repository.ProductRepository;
import com.api.springdatajpa.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public Optional<ProductDto> createProduct(ProductRequest productRequest) {
        return findProductByName(productRequest.getProductName())
                .map(product -> updateProductById(product.getId(), productRequest))
                .orElseGet(() -> Optional.ofNullable(productRepository.save(productRequest.toProductEntity()).toProductResponse()));
    }


    @Override
    public Optional<ProductDto> findProductById(Long productId) {
        return Optional.of(productRepository.findById(productId).orElseThrow(
                () -> new CustomNotFoundException("No product Id " + productId + " found")
        ).toProductResponse());
    }

    @Override
    public void deleteProductById(Long productId) {
        findProductById(productId).ifPresent(product -> productRepository.deleteById(productId));
    }

    @Override
    public List<ProductDto> findAllProducts(Integer pageNo, Integer pageSize, Enums.ProductSortBy sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy.name());
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return productRepository.findAll(pageable).getContent()
                .stream().map(Product::toProductResponse).toList();
    }

    @Override
    public Optional<ProductDto> updateProductById(Long productId, ProductRequest productRequest) {
        findProductByName(productRequest.getProductName()).ifPresent(product -> {
            if (!productId.equals(product.getId())) {
                throw new CustomNotFoundException("This product name is already in use");
            }
        });
        return findProductById(productId).map(
                product -> productRepository.save(productRequest.toProductEntity(productId)).toProductResponse());
    }

    @Override
    public List<ProductDto> findProductByOrderId(Long orderId) {
        return productRepository.findProductsByOrderId(orderId).stream().map(Product::toProductResponse).toList();
    }

    public Optional<ProductDto> findProductByName(String name) {
        return productRepository.findProductByProductName(name).map(Product::toProductResponse);
    }
}
