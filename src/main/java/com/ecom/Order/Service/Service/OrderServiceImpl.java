package com.ecom.Order.Service.Service;

import com.ecom.Order.Service.DTO.OrderDto;
import com.ecom.Order.Service.Entity.Order;
import com.ecom.Order.Service.Entity.Outbox;
import com.ecom.Order.Service.Repository.OrderRepository;
import com.ecom.Order.Service.Repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OutboxRepository outboxRepository, ObjectMapper objectMapper){
        this.orderRepository = orderRepository;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderDto orderDto) throws JsonProcessingException {
        Order order = Order.builder()
                .customerId(orderDto.getCustomerId())
                .price(orderDto.getPrice())
                .productId(orderDto.getProductId())
                .quantity(orderDto.getQuantity())
                .build();

        order = orderRepository.save(order);
        log.info("Order created: {}", order);
        // we need to put this order in Outbox table for Kafka publishing
        String payload = objectMapper.writeValueAsString(order);
        Outbox outbox = Outbox.builder()
                .topic("Orders")
                .payload(payload)
                .build();
        outboxRepository.save(outbox);
        return order;
    }

    @Override
    public Order getOrderByOrderId(int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("No order present"));
        log.info("Order is retrieved from database");
        return order;
    }

    @Override
    public List<Order> getOrderByCustomerId(int customerId) {
        List<Order> orderList = orderRepository.findByCustomerId(customerId);
        log.info("Order List based on customerId {}, is: {}", customerId, orderList);
        return orderList;
    }
}
