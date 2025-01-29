package com.ecom.Order.Service.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentUpdate {
    public int paymentId;
    public int orderId;
    public int customerId;
    public String status;
    public double amount;
}
