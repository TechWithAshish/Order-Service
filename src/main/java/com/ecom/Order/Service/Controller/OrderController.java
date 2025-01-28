package com.ecom.Order.Service.Controller;


import com.ecom.Order.Service.DTO.OrderDto;
import com.ecom.Order.Service.Entity.Order;
import com.ecom.Order.Service.Service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) throws JsonProcessingException {
        Order order = orderService.createOrder(orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/order")
    public ResponseEntity<?> getOrder(@RequestParam(value = "orderId", required = false) Integer orderId, @RequestParam(value = "customerId", required = false) Integer customerId) throws BadRequestException {
        if(orderId != null && customerId != null){
            // need to throw error that we can't find user with both query parameter...
            throw new BadRequestException("Invalid query parameter. Request correctly");
        }else if(orderId != null){
            // get details based on orderId....
            Order orderByOrderId = orderService.getOrderByOrderId(orderId);
            return new ResponseEntity<>(orderByOrderId, HttpStatus.OK);
        }else if(customerId != null){
            // get details based on customerId....
            List<Order> orderByCustomerId = orderService.getOrderByCustomerId(customerId);
            return new ResponseEntity<>(orderByCustomerId, HttpStatus.OK);
        }else{
            throw new BadRequestException("No query parameter is present");
        }
    }
}
