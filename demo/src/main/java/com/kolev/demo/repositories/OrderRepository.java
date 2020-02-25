package com.kolev.demo.repositories;

import com.kolev.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findByOrderName(String order_name);
}
