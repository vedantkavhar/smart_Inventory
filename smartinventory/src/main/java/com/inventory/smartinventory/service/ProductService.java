package com.inventory.smartinventory.service;

import java.util.List;

import com.inventory.smartinventory.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
