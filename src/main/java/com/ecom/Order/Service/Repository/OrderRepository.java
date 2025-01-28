package com.ecom.Order.Service.Repository;

import com.ecom.Order.Service.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findByCustomerId(int customerId);
}
