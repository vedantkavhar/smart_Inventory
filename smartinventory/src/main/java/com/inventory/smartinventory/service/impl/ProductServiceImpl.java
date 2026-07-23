package com.inventory.smartinventory.service.impl;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventory.smartinventory.dto.ProductDto;
import com.inventory.smartinventory.entity.Category;
import com.inventory.smartinventory.entity.Product;
import com.inventory.smartinventory.exception.ResourceNotFoundException;
import com.inventory.smartinventory.repository.CategoryRepository;
import com.inventory.smartinventory.repository.ProductRepository;
import com.inventory.smartinventory.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Helper: convert Product entity to ProductDto
    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        return dto;
    }

    @Override
    public ProductDto saveProduct(ProductDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));

        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }

    @Override
    public Page<ProductDto> getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));

        BeanUtils.copyProperties(dto, product, "id");
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public Page<ProductDto> searchByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContainingIgnoreCase(name, pageable).map(this::mapToDto);
    }

    @Override
    public Page<ProductDto> filterByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoryId(categoryId, pageable).map(this::mapToDto);
    }

    @Override
    public Page<ProductDto> filterByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByStatusIgnoreCase(status, pageable).map(this::mapToDto);
    }

    @Override
    public Page<ProductDto> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable).map(this::mapToDto);
    }
}
