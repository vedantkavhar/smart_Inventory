package com.inventory.product.repository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.product.entity.Category;
public interface CategoryRepository extends JpaRepository<Category,Long> { Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable); }
