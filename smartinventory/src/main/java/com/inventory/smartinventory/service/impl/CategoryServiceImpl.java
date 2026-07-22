package com.inventory.smartinventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //step 6 addign resouce not foind custome exception and it extens runtimeexceotino ,its use
    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Category not found with id : " + id));

    }

    //step 4 adding udpate categrory method using id
//    first find by id then set valudes usign setters 
    @Override
    public Category updateCategory(Long id, Category category) {

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
    	Category existingCategory = categoryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Category not found with id : " + id));

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        return categoryRepository.save(existingCategory);
    	
    }
    
//    step 5 delte category usign id
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
    	
    	 Category existingCategory = categoryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Category not found with id : " + id));

    	    categoryRepository.delete(existingCategory);
    }
    

}