package com.inventory.smartinventory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.smartinventory.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Search categories by name (case-insensitive, partial match)
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}