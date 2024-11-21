package com.darkra.course.resources;

import com.darkra.course.entities.User;
import com.darkra.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//controller responsible for handling HTTP requests related to user
@RestController //annotation defines that this is a REST controller
@RequestMapping(value = "/users") //base URL mapping for all endpoints
public class UserResource {

    //injection for the service layer handling user-related operations
    @Autowired
    private UserService userService;

    //endpoint that retrieves all users
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //endpoint that retrieves a user by its id
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //endpoint that inserts a new user
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //endpoint that deletes a user by its id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //endpoint that updates a user by its id
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = userService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }





}
