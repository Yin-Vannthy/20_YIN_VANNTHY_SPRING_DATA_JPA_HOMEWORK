package com.api.springdatajpa.controller;

import com.api.springdatajpa.model.dto.ProductDto;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.ProductRequest;
import com.api.springdatajpa.model.response.ApiResponse;
import com.api.springdatajpa.service.ProductService;
import com.api.springdatajpa.util.APIResponseUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ApiResponse<Optional<ProductDto>>> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        productService.createProduct(productRequest),
                        HttpStatus.CREATED
        ));
    }

    @GetMapping("/findProductById/{productId}")
    public ResponseEntity<ApiResponse<Optional<ProductDto>>> findProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        productService.findProductById(productId),
                        HttpStatus.OK
        ));
    }

    @DeleteMapping("/deleteProductById/{productId}")
    public ResponseEntity<ApiResponse<?>> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        null,
                        HttpStatus.OK
        ));
    }

    @GetMapping("/findAllProducts")
    public ResponseEntity<ApiResponse<List<ProductDto>>> findAllProducts(
            @RequestParam(defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "productName", required = false) Enums.ProductSortBy sortBy,
            @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortDirection
    ) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        productService.findAllProducts(pageNo, pageSize, sortBy, sortDirection),
                        HttpStatus.OK
        ));
    }

    @PutMapping("/updateProductById/{productId}")
    public ResponseEntity<ApiResponse<Optional<ProductDto>>> updateProductById(@PathVariable Long productId, @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        productService.updateProductById(productId, productRequest),
                        HttpStatus.OK
                )
        );
    }
}
