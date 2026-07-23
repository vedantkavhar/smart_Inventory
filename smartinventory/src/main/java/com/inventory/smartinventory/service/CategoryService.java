package com.inventory.smartinventory.service;

import java.util.List;

import com.inventory.smartinventory.dto.CategoryDto;

public interface CategoryService {
// step 9 created dtos ,use dtos till entity
	CategoryDto saveCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(Long id,
            CategoryDto categoryDto);

    void deleteCategory(Long id);

}