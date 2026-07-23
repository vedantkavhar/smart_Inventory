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
import com.inventory.smartinventory.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> saveCategory(
            @Valid @RequestBody CategoryRequestDTO dto) {

        CategoryResponseDTO response =
                categoryService.saveCategory(dto);

        ApiResponse<CategoryResponseDTO> apiResponse =
                new ApiResponse<>(
                        true,
                        "Category created successfully",
                        response);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // step 9 categorty -> categoryResponseDto ,
//    getting / recinving list from db

 // CHANGED : Return ResponseEntity
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAllCategories() {

        ApiResponse<List<CategoryResponseDTO>> response =
                new ApiResponse<>(
                        true,
                        "Categories fetched successfully",
                        categoryService.getAllCategories());

        return ResponseEntity.ok(response);
    }

    // step 9 usecategory -> categoryResponseDTO  ,
//    getting /receiving cat from db
 // CHANGED : Return ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(
            @PathVariable Long id) {

        ApiResponse<CategoryResponseDTO> response =
                new ApiResponse<>(
                        true,
                        "Category fetched successfully",
                        categoryService.getCategoryById(id));

        return ResponseEntity.ok(response);
    }
    
    
  //    step 4 update category
 // step 9 category -> categoryRequestDto ,
//  store incoming data ,use reqdto till in service layer
 // CHANGED : Return ResponseEntity
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO dto) {

        ApiResponse<CategoryResponseDTO> response =
                new ApiResponse<>(
                        true,
                        "Category updated successfully",
                        categoryService.updateCategory(id, dto));

        return ResponseEntity.ok(response);
    }
    
    
    
//    step 5 deleting category usign id/
 // CHANGED : Return 204 No Content

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        "Category deleted successfully",
                        null);

        return ResponseEntity.ok(response);
    }

}