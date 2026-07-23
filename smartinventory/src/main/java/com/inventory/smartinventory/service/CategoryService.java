package com.inventory.smartinventory.service;

import org.springframework.data.domain.Page;

import com.inventory.smartinventory.dto.CategoryDto;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);

    // Get all with pagination and sorting
    Page<CategoryDto> getAllCategories(int page, int size, String sortBy, String sortDir);

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);

    // Search by name with pagination
    Page<CategoryDto> searchByName(String name, int page, int size);
}