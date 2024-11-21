package com.darkra.course.resources;

import com.darkra.course.entities.Order;
import com.darkra.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//controller responsible for handling HTTP requests related to order
@RestController //annotation defines that this is a REST controller
@RequestMapping(value = "/orders") //base URL mapping for all endpoints in this controller
public class OrderResource {

    //injection for the service layer handling order-related operations
    @Autowired
    private OrderService orderService;

    //endpoint that retrieves all orders
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = orderService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //endpoint that retrieve an order by its id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = orderService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //endpoint that inserts a new order
    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order obj){
        obj = orderService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //endpoint that deletes an order by its id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
