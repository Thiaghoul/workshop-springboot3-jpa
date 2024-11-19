package com.darkra.course.services;

import com.darkra.course.entities.Category;
import com.darkra.course.entities.Product;
import com.darkra.course.repositories.CategoryRepository;
import com.darkra.course.repositories.ProductRepository;
import com.darkra.course.services.exceptions.DatabaseException;
import com.darkra.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product insert(Product obj){
        return productRepository.save(obj);
    }

    public Product findById(Long id){
       Optional<Product> obj = productRepository.findById(id);
       return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        try{
            productRepository.deleteById(id);

        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product obj){
        try{
            Product entity = productRepository.getReferenceById(id);
            updateData(entity, obj);
            return productRepository.save(entity);

        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Product entity, Product obj){
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setImgUrl(obj.getImgUrl());

    }
}
