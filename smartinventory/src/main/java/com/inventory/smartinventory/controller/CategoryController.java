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

import com.inventory.smartinventory.dto.CategoryRequestDTO;
import com.inventory.smartinventory.dto.CategoryResponseDTO;
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

    // step 9 categorty -> categoryRequestDto ,
//    store incoming data ,use reqdto till in service layer
    @PostMapping
    public CategoryResponseDTO saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.saveCategory(categoryRequestDTO);
    }

    // step 9 categorty -> categoryResponseDto ,
//    getting / recinving list from db

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // step 9 usecategory -> categoryResponseDTO  ,
//    getting /receiving cat from db
    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    
    
  //    step 4 update category
 // step 9 category -> categoryRequestDto ,
//  store incoming data ,use reqdto till in service layer
    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO  categoryRequestDTO) {

        return categoryService.updateCategory(id, categoryRequestDTO);
    }
    
    
    
//    step 5 deleting category usign id/
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

    }
}