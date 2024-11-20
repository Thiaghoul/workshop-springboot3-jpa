package com.darkra.course.services;

import com.darkra.course.entities.Order;
import com.darkra.course.entities.User;
import com.darkra.course.repositories.OrderRepository;
import com.darkra.course.repositories.UserRepository;
import com.darkra.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//class responsible for the logic of the entity Order
@Service
public class OrderService {

    //injection of the order repository to interact with database
    @Autowired
    private OrderRepository orderRepository;

    //retrieves all orders from the database
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    //insert a new order into the data
    public Order insert(Order obj){
        return orderRepository.save(obj);
    }

    //retrieve a specified order by its id and throws an exception if not found
    public Order findById(Long id){
        Optional<Order> obj =  orderRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //deletes an existent order if id given is found, throws an exception if not found
    public void delete(Long id){
        try{
            orderRepository.deleteById(id);

        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);

        }
    }
}
