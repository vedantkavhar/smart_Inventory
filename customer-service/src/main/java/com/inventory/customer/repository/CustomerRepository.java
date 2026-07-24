package com.inventory.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
