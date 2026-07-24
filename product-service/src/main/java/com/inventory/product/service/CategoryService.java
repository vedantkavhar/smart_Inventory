package com.inventory.product.service;
import org.springframework.data.domain.Page;
import com.inventory.product.dto.CategoryDto;
public interface CategoryService { CategoryDto saveCategory(CategoryDto dto); Page<CategoryDto> getAllCategories(int page,int size,String sortBy,String sortDir); CategoryDto getCategoryById(Long id); CategoryDto updateCategory(Long id,CategoryDto dto); void deleteCategory(Long id); Page<CategoryDto> searchByName(String name,int page,int size); }
