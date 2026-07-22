package com.inventory.smartinventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.smartinventory.entity.Category;
import com.inventory.smartinventory.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category saveCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    
    
//    step 4 update category
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @Valid @RequestBody Category category) {

        return categoryService.updateCategory(id, category);
    }
    
    
    
//    step 5 deleting category usign id/
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

    }
}