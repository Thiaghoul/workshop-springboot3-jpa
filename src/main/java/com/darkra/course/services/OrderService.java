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

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order insert(Order obj){
        return orderRepository.save(obj);
    }

    public Order findById(Long id){
        Optional<Order> obj =  orderRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id){
        try{
            orderRepository.deleteById(id);

        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);

        }
    }
}
