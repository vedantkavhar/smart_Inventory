package com.inventory.product.repository;
import java.math.BigDecimal;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.product.entity.Product;
public interface ProductRepository extends JpaRepository<Product,Long> { Page<Product> findByNameContainingIgnoreCase(String name,Pageable pageable); Page<Product> findByCategoryId(Long categoryId,Pageable pageable); Page<Product> findByStatusIgnoreCase(String status,Pageable pageable); Page<Product> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice,Pageable pageable); }
