package com.api.springdatajpa.repository;

import com.api.springdatajpa.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductName(String name);

    @Query(value = """
            SELECT p.* FROM product p JOIN product_order ON p.id = product_order.product_id
                    JOIN order_tb o ON o.order_id = product_order.order_id
                    WHERE o.order_id = :orderId
        """,
    nativeQuery = true)
    List<Product> findProductsByOrderId(@Param("orderId") Long orderId);
}
