package com.inventory.product.service;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import com.inventory.product.dto.ProductDto;
public interface ProductService { ProductDto saveProduct(ProductDto dto); Page<ProductDto> getAllProducts(int page,int size,String sortBy,String sortDir); ProductDto getProductById(Long id); ProductDto updateProduct(Long id,ProductDto dto); void deleteProduct(Long id); Page<ProductDto> searchByName(String name,int page,int size); Page<ProductDto> filterByCategory(Long categoryId,int page,int size); Page<ProductDto> filterByStatus(String status,int page,int size); Page<ProductDto> filterByPriceRange(BigDecimal minPrice,BigDecimal maxPrice,int page,int size); }
