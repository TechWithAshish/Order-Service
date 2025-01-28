package com.ecom.Order.Service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String topic;
    public String payload;
    public LocalDateTime createAt = LocalDateTime.now();
}
