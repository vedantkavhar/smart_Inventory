package com.inventory.smartinventory.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.smartinventory.dto.ProductDto;
import com.inventory.smartinventory.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto dto) {
        ProductDto response = productService.saveProduct(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET /api/products?page=0&size=10&sortBy=name&sortDir=asc
    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        return ResponseEntity.ok(productService.getAllProducts(page, size, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/products/search?name=phone&page=0&size=5
    @GetMapping("/search")
    public ResponseEntity<Page<ProductDto>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(productService.searchByName(name, page, size));
    }

    // GET /api/products/filter/category?categoryId=1&page=0&size=5
    @GetMapping("/filter/category")
    public ResponseEntity<Page<ProductDto>> filterByCategory(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(productService.filterByCategory(categoryId, page, size));
    }

    // GET /api/products/filter/status?status=ACTIVE&page=0&size=5
    @GetMapping("/filter/status")
    public ResponseEntity<Page<ProductDto>> filterByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(productService.filterByStatus(status, page, size));
    }

    // GET /api/products/filter/price?minPrice=100&maxPrice=1000&page=0&size=5
    @GetMapping("/filter/price")
    public ResponseEntity<Page<ProductDto>> filterByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(productService.filterByPriceRange(minPrice, maxPrice, page, size));
    }
}
