package com.inventory.product.service.impl;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.inventory.product.dto.CategoryDto;
import com.inventory.product.entity.Category;
import com.inventory.product.exception.ResourceNotFoundException;
import com.inventory.product.repository.CategoryRepository;
import com.inventory.product.service.CategoryService;
@Service public class CategoryServiceImpl implements CategoryService { private final CategoryRepository categoryRepository; public CategoryServiceImpl(CategoryRepository categoryRepository){this.categoryRepository=categoryRepository;} public CategoryDto saveCategory(CategoryDto dto){Category category=new Category();BeanUtils.copyProperties(dto,category);return toDto(categoryRepository.save(category));} public Page<CategoryDto> getAllCategories(int page,int size,String sortBy,String sortDir){Sort sort=sortDir.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();return categoryRepository.findAll(PageRequest.of(page,size,sort)).map(this::toDto);} public CategoryDto getCategoryById(Long id){return toDto(category(id));} public CategoryDto updateCategory(Long id,CategoryDto dto){Category category=category(id);category.setName(dto.getName());category.setDescription(dto.getDescription());return toDto(categoryRepository.save(category));} public void deleteCategory(Long id){categoryRepository.delete(category(id));} public Page<CategoryDto> searchByName(String name,int page,int size){return categoryRepository.findByNameContainingIgnoreCase(name,PageRequest.of(page,size)).map(this::toDto);} private Category category(Long id){return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found with id: "+id));} private CategoryDto toDto(Category category){CategoryDto dto=new CategoryDto();BeanUtils.copyProperties(category,dto);return dto;} }
