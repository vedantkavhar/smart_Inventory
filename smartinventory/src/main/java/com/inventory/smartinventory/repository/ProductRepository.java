package com.inventory.smartinventory.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.smartinventory.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search products by name (case-insensitive, partial match)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Filter by category ID with pagination
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    // Filter by status with pagination
    Page<Product> findByStatusIgnoreCase(String status, Pageable pageable);

    // Filter by price range with pagination
    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}
