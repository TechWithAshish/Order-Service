package com.ecom.Order.Service.Event;

import com.ecom.Order.Service.Entity.PaymentUpdate;
import com.ecom.Order.Service.Service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentProcessedConsumer {

    private final OrderService orderService;

    public PaymentProcessedConsumer(OrderService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(topics = "PaymentStatus", groupId = "group-1", containerFactory = "paymentKafkaListenerContainerFactory")
    public void paymentUpdate(PaymentUpdate paymentUpdate) throws JsonProcessingException {
        log.info("Listing to kafka topic PaymentStatus with event Payment: {}", paymentUpdate.toString());
        orderService.updateOrderStatus(paymentUpdate);
    }

}
