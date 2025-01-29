package com.ecom.Order.Service.Service;

import com.ecom.Order.Service.DTO.OrderDto;
import com.ecom.Order.Service.Entity.Order;
import com.ecom.Order.Service.Entity.PaymentUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderDto orderDto) throws JsonProcessingException;
    public Order getOrderByOrderId(int orderId);
    public List<Order> getOrderByCustomerId(int customerId);
    public void updateOrderStatus(PaymentUpdate paymentUpdate) throws JsonProcessingException;
}
