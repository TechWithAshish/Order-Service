package com.ecom.Order.Service.Event;

import com.ecom.Order.Service.Entity.Outbox;
import com.ecom.Order.Service.Repository.OutboxRepository;
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
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    public OutboxPublisher(OutboxRepository outboxRepository, KafkaTemplate<String, Object> kafkaTemplate){
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void publishOutboxEvents(){
        List<Outbox> outboxList = outboxRepository.findAll();
        for(Outbox outbox : outboxList){
            log.info("publishing event to kafka for topic {}, and payload {}", outbox.getTopic(), outbox.getPayload());
            kafkaTemplate.send(outbox.getTopic(), outbox.getPayload());
            outboxRepository.delete(outbox);
        }
    }
}
