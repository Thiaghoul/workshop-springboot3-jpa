package com.darkra.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

//class used to represent an entity of the database with the table "tb_category"
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    //attribute used as the primary key of the entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //annotation defines this attribute as auto increment
    private Long id;

    @Column(unique = true) //annotation defines the column as unique
    private String name;

    //it maps the products attribute with a relation "ManyToMany" with the Product Entity
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore // annotation used to make sure loop doesn't happen in the JSON serialization
    private Set<Product> products = new HashSet<>();


    //constructors
    public Category(){

    }

    public Category(Long id, String name){
        this.id = id;
        this.name = name;
    }

    //getters and setters methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    //hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
