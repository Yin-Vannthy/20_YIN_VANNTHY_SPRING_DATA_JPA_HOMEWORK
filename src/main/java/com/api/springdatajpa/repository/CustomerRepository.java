package com.api.springdatajpa.repository;

import com.api.springdatajpa.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(
            value = "SELECT * FROM customer c JOIN email e ON c.email_id = e.id WHERE e.email = :email",
            nativeQuery = true
    )
    Optional<Customer> findCustomerByEmail(@Param("email") String email);
}
