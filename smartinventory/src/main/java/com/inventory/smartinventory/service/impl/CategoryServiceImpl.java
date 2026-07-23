package com.inventory.smartinventory.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inventory.smartinventory.dto.CategoryRequestDTO;
import com.inventory.smartinventory.dto.CategoryResponseDTO;
import com.inventory.smartinventory.entity.Category;
import com.inventory.smartinventory.exception.ResourceNotFoundException;
import com.inventory.smartinventory.repository.CategoryRepository;
import com.inventory.smartinventory.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    
//    step 10
//    adding mapping
    //reqdto to entity
    private Category mapToEntity(CategoryRequestDTO dto) {

        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return category;
    }
    
    //step 10:entiry to respdto mapping
    private CategoryResponseDTO mapToResponseDTO(Category category) {

        CategoryResponseDTO dto = new CategoryResponseDTO();

        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        return dto;
    }
    
    
    
    //step 9
//    Service layer is responsible for business logic and mapping dto with entity .
    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO dto) {

    	/*
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
       */
    	
    	
    	//step 10:give dto to maptoentity fn it will concevert and return cat entity
    	 Category category = mapToEntity(dto);
        
        

        Category savedCategory = categoryRepository.save(category);

        /*
        CategoryResponseDTO response = new CategoryResponseDTO();

        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());
        response.setDescription(savedCategory.getDescription());
        return response;
		*/
        
        return mapToResponseDTO(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    //step 6 addign resouce not foind custome exception and it extens runtimeexceotino ,its use
    @Override
    public CategoryResponseDTO getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id : " + id));

        return mapToResponseDTO(category);
    }

    //step 4 adding udpate categrory method using id
//    first find by id then set valudes usign setters 
    @Override
    public CategoryResponseDTO updateCategory(Long id,
            CategoryRequestDTO dto) {

    	/*
        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
        		
        	//set new upcoming values
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());

            //save used for insert and update both
            return categoryRepository.save(existingCategory);
        }

        return null;
        */
    	
    	//step 7 usign respurcenot found cusomrexcpetion
    	Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id : " + id));

        existingCategory.setName(dto.getName());
        existingCategory.setDescription(dto.getDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);

        return mapToResponseDTO(updatedCategory);
    	
    }
    
//    step 5 delete category usign id
//    first find by id,then delete 
    @Override
    public void deleteCategory(Long id) {

    	/*
        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
            categoryRepository.delete(existingCategory);
        }
        
        */
    	
//    	step 7 usign resourcenot found cusomrexcpetion
    	
    	Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id : " + id));

        categoryRepository.delete(existingCategory);
    }
    

}