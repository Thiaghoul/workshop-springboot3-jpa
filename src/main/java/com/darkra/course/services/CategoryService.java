package com.darkra.course.services;

import com.darkra.course.entities.Category;
import com.darkra.course.entities.Order;
import com.darkra.course.repositories.CategoryRepository;
import com.darkra.course.repositories.OrderRepository;
import com.darkra.course.services.exceptions.DatabaseException;
import com.darkra.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//class responsible for the logic of the entity Category
@Service
public class CategoryService {

    //injection of the category repository to interact with the database
    @Autowired
    private CategoryRepository categoryRepository;

    //retrieves all categories from the database
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    //inserts a new category into the database
    public Category insert(Category obj){
        return categoryRepository.save(obj);
    }

    //retrieve a specified category by its id and throws an exception if not found
    public Category findById(Long id){
       Optional<Category> obj = categoryRepository.findById(id);
       return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //deletes an existent category if id given is found, throws an exception if not found
    public void delete(Long id){
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        try{
            categoryRepository.deleteById(id);

        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    //updates an existing category by its id and throws an exception if not found
    public Category update(Long id, Category obj){
        try{
            Category entity = categoryRepository.getReferenceById(id);
            updateData(entity, obj);
            return categoryRepository.save(entity);

        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    //method to help update specific attribute of a category
    public void updateData(Category entity, Category obj){
        entity.setName(obj.getName());
    }



}
