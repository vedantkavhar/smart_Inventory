package com.inventory.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.smartinventory.entity.Category;
import com.inventory.smartinventory.repository.CategoryRepository;
import com.inventory.smartinventory.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

}