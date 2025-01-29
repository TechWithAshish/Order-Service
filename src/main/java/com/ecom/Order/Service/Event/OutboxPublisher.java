package com.ecom.Order.Service.Event;

import com.ecom.Order.Service.Config.KafkaProducerService;
import com.ecom.Order.Service.Entity.Order;
import com.ecom.Order.Service.Entity.OutboxOrder;
import com.ecom.Order.Service.Repository.OutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final KafkaProducerService<Order> orderKafkaProducerService;
    private final ObjectMapper objectMapper;
    @Autowired
    public OutboxPublisher(OutboxRepository outboxRepository, KafkaProducerService<Order> orderKafkaProducerService, ObjectMapper objectMapper){
        this.outboxRepository = outboxRepository;
        this.orderKafkaProducerService = orderKafkaProducerService;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void publishOutboxEvents(){
        List<OutboxOrder> outboxOrderList = outboxRepository.findAll();
        for(OutboxOrder outboxOrder : outboxOrderList){
            try{
                Order order = objectMapper.readValue(outboxOrder.getPayload(), Order.class);
                log.info("publishing event from publishOutboxEvents to kafka for topic {}, and payload of Order {}", outboxOrder.getTopic(), outboxOrder.getPayload());

                orderKafkaProducerService.sendMessage(outboxOrder.getTopic(), order);
                outboxRepository.delete(outboxOrder);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
