package com.inventory.smartinventory.service;

import java.util.List;

import com.inventory.smartinventory.entity.Category;

public interface CategoryService {

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);

}