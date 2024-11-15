package com.darkra.course.services;

import com.darkra.course.entities.Category;
import com.darkra.course.entities.Order;
import com.darkra.course.repositories.CategoryRepository;
import com.darkra.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
       Optional<Category> obj = categoryRepository.findById(id);
       return obj.get();
    }
}
