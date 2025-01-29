package com.ecom.Order.Service.Repository;

import com.ecom.Order.Service.Entity.OutboxOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxOrder, Integer> {
}
