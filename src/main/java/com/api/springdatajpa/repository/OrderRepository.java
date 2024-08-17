package com.api.springdatajpa.repository;

import com.api.springdatajpa.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId")
    Optional<List<Order>> findOrdersByCustomerId(Long customerId);
}
