package com.inventory.order.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.order.entity.Order;
public interface OrderRepository extends JpaRepository<Order, Long> { }
