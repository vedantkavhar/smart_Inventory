package com.inventory.smartinventory.service;

import java.util.List;

import com.inventory.smartinventory.dto.CategoryRequestDTO;
import com.inventory.smartinventory.dto.CategoryResponseDTO;

public interface CategoryService {
// step 9 created dtos ,use dtos till entity
	CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(Long id);

    CategoryResponseDTO updateCategory(Long id,
            CategoryRequestDTO categoryRequestDTO);

    void deleteCategory(Long id);

}