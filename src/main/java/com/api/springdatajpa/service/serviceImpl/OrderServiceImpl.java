package com.api.springdatajpa.service.serviceImpl;

import com.api.springdatajpa.exception.CustomNotFoundException;
import com.api.springdatajpa.model.dto.OrderDto;
import com.api.springdatajpa.model.dto.ProductDto;
import com.api.springdatajpa.model.entity.Customer;
import com.api.springdatajpa.model.entity.Order;
import com.api.springdatajpa.model.entity.ProductOrder;
import com.api.springdatajpa.model.enums.Enums;
import com.api.springdatajpa.model.request.OrderRequest;
import com.api.springdatajpa.repository.OrderRepository;
import com.api.springdatajpa.repository.ProductOrderRepository;
import com.api.springdatajpa.service.CustomerService;
import com.api.springdatajpa.service.OrderService;
import com.api.springdatajpa.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductOrderRepository productOrderRepository;
    private final ProductService productService;

    @Override
    public OrderDto createOrder(Long customerId, List<OrderRequest> orderRequests) {
        Customer customer = customerService.findCustomerById(customerId).orElseThrow(
                () -> new CustomNotFoundException("Customer with Id : " + customerId + " not found.")
        ).toCustomerEntity();

        Order order = new Order(customer);

        List<ProductOrder> productOrders = new ArrayList<>();
        List<ProductDto> products = new ArrayList<>();

        order.setTotalAmount(orderRequests.stream().map(request -> {
                ProductDto productDto = productService.findProductById(request.getProductId())
                        .orElseThrow(
                                () -> new CustomNotFoundException("Product with Id : " + request.getProductId() + " not found.")
                        );

                products.add(productDto);
                productOrders.add(new ProductOrder(request.getQuantity(), productDto.toProductEntity(), order));

                return productDto.getUnitPrice() * request.getQuantity();
            }
        ).reduce(0F, Float::sum));

        // Save the order and associated product orders
        orderRepository.save(order);
        productOrderRepository.saveAll(productOrders);

        return order.toOrderResponse(products);
    }

    @Override
    public OrderDto updateOrderStatusByOrderId(Long orderId, Enums.Status status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomNotFoundException("No order with Id : " + orderId + " found."));

        order.setStatus(status);
        return orderRepository.save(order).toOrderResponse(productService.findProductByOrderId(orderId));
    }

    @Override
    public Optional<OrderDto> findOrderById(Long orderId) {
        return Optional.ofNullable(orderRepository.findById(orderId)
                .map(order -> order.toOrderResponse(productService.findProductByOrderId(orderId)))
                .orElseThrow(
                        () -> new CustomNotFoundException("No order with Id : " + orderId + " found.")
                ));
    }

    @Override
    public List<OrderDto> findOrderByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findOrdersByCustomerId(customerId)
                .orElseThrow(() -> new CustomNotFoundException("No orders found for customer with Id : " + customerId));

        return orders.stream()
                .map(order -> order.toOrderResponse(productService.findProductByOrderId(order.getOrderId())))
                .collect(Collectors.toList());
    }

}
