package com.inventory.smartinventory.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventory.smartinventory.dto.CategoryDto;
import com.inventory.smartinventory.entity.Category;
import com.inventory.smartinventory.exception.ResourceNotFoundException;
import com.inventory.smartinventory.repository.CategoryRepository;
import com.inventory.smartinventory.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto saveCategory(CategoryDto dto) {
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        Category savedCategory = categoryRepository.save(category);

        CategoryDto responseDto = new CategoryDto();
        BeanUtils.copyProperties(savedCategory, responseDto);
        return responseDto;
    }

    @Override
    public Page<CategoryDto> getAllCategories(int page, int size, String sortBy, String sortDir) {
        // Build sort direction: asc or desc
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return categoryRepository.findAll(pageable)
                .map(category -> {
                    CategoryDto dto = new CategoryDto();
                    BeanUtils.copyProperties(category, dto);
                    return dto;
                });
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id : " + id));

        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(category, dto);
        return dto;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id : " + id));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(category);

        CategoryDto responseDto = new CategoryDto();
        BeanUtils.copyProperties(updated, responseDto);
        return responseDto;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id : " + id));

        categoryRepository.delete(category);
    }

    @Override
    public Page<CategoryDto> searchByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return categoryRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(category -> {
                    CategoryDto dto = new CategoryDto();
                    BeanUtils.copyProperties(category, dto);
                    return dto;
                });
    }
}