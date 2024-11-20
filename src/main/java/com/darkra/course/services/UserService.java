package com.darkra.course.services;

import com.darkra.course.entities.User;
import com.darkra.course.repositories.UserRepository;
import com.darkra.course.services.exceptions.DatabaseException;
import com.darkra.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//class responsible for the logic of the entity User
@Service
public class UserService {

    //injection of the user repository to interact with the database
    @Autowired
    private UserRepository userRepository;

    //retrieves all users from the database
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //inserts a new user into the database
    public User insert(User obj){
        return userRepository.save(obj);
    }

    //retrieves a user by its id and throws an exception if not found
    public User findById(Long id){
       Optional<User> obj = userRepository.findById(id);
       return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //deletes an user by its id and throws an exception if not found
    public void delete(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        try{
            userRepository.deleteById(id);

        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    //updates an existing user by its id and throws an exception if not found
    public User update(Long id, User obj){
        try{
            User entity = userRepository.getReferenceById(id);
            updateData(entity, obj);
            return userRepository.save(entity);

        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);

        }
    }

    //method to help update specific attribute of a user
    private void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }



}
