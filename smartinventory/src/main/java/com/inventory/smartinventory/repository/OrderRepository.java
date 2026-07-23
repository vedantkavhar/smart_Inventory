package com.inventory.smartinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.smartinventory.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
