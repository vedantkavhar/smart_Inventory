package com.inventory.smartinventory.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.inventory.smartinventory.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);

    // Get all with pagination and sorting
    Page<ProductDto> getAllProducts(int page, int size, String sortBy, String sortDir);

    ProductDto getProductById(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);

    // Search by name
    Page<ProductDto> searchByName(String name, int page, int size);

    // Filter by category
    Page<ProductDto> filterByCategory(Long categoryId, int page, int size);

    // Filter by status
    Page<ProductDto> filterByStatus(String status, int page, int size);

    // Filter by price range
    Page<ProductDto> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, int page, int size);
}
