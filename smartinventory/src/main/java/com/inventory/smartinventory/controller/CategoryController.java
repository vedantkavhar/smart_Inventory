package com.inventory.smartinventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
     // CHANGED : Return ResponseEntity
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> saveCategory(
            @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {

        CategoryResponseDTO response =
                categoryService.saveCategory(categoryRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // step 9 categorty -> categoryResponseDto ,
//    getting / recinving list from db

 // CHANGED : Return ResponseEntity
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // step 9 usecategory -> categoryResponseDTO  ,
//    getting /receiving cat from db
 // CHANGED : Return ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoryService.getCategoryById(id));
    }
    
    
  //    step 4 update category
 // step 9 category -> categoryRequestDto ,
//  store incoming data ,use reqdto till in service layer
 // CHANGED : Return ResponseEntity
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {

        return ResponseEntity.ok(
                categoryService.updateCategory(id, categoryRequestDTO));
    }
    
    
    
//    step 5 deleting category usign id/
 // CHANGED : Return 204 No Content

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }

}