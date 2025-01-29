package com.ecom.Order.Service.Entity;

import jakarta.persistence.*;
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
public class OutboxOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String topic;
    @Lob
    public String payload;
    public LocalDateTime createAt = LocalDateTime.now();
}
