package com.example.bookmicroservices.services;

import com.example.bookmicroservices.exceptions.CategoryAlreadyExistsException;
import com.example.bookmicroservices.Repositories.CategoryRepository;
import com.example.bookmicroservices.exceptions.CategoryNotFoundException;
import com.example.bookmicroservices.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category saveCategory(Category newCategory) {
        try {
            return categoryRepository.save(newCategory);
        } catch (Exception e) {
            throw new CategoryAlreadyExistsException("Category '"+newCategory.getName()+"' already exists");
        }
    }

    public List<String> getBookCategories (){
        try{
            List<String> categories = new ArrayList<String>();
            Iterable<Category> tempCategory =  categoryRepository.findAll();
            for (Category c : tempCategory) {
                categories.add(c.getName());
            }
            return categories;
        } catch (Exception e){
            throw new CategoryNotFoundException("No categories exist");
        }
    }

}
