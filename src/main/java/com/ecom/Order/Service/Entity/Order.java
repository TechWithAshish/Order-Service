package com.ecom.Order.Service.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int orderId;
    public int customerId;
    public int productId;
    public int quantity;
    public double price;
    public String status;
    public int paymentId;

    public Order(){

    }
}
