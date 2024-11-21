package com.darkra.course.resources;

import com.darkra.course.entities.Category;
import com.darkra.course.entities.Product;
import com.darkra.course.services.CategoryService;
import com.darkra.course.services.ProductService;
import com.darkra.course.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//controller responsible for handling HTTP requests related to product
@RestController //annotation defines that this is a REST controller
@RequestMapping(value = "/products") //base URL mapping for all endpoints
public class ProductResource {

    //injection for the service layer handling product-related operations
    @Autowired
    private ProductService productService;

    //endpoint that retrieves all products
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //endpoint that retrieves a product by its id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = productService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

   //endpoint that inserts a new product
    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj){
        obj = productService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //endpoint that deletes a product by its id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //endpoint that updates a product by its id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product obj){
        obj = productService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
