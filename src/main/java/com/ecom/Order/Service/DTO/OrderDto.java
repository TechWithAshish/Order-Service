package com.ecom.Order.Service.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    public int customerId;
    public int productId;
    public int quantity;
    public double price;
}
