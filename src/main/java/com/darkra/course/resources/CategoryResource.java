package com.darkra.course.resources;

import com.darkra.course.entities.Category;
import com.darkra.course.entities.Order;
import com.darkra.course.services.CategoryService;
import com.darkra.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//controller responsible for handling HTTP requests related to category
@RestController //annotation defines that this is a REST controller
@RequestMapping(value = "/categories") //base URL mapping for all endpoints in this controller
public class CategoryResource {

    //injection for the service layer handling category-related operations
    @Autowired
    private CategoryService categoryService;

    //endpoint that retrieves all categories
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //endpoint that retrieve a category by its id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //endpoint that inserts a new category
    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category obj){
        obj = categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //endpoint that deletes a category by its id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //endpoint to update a category by its id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj){
        obj = categoryService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


}
