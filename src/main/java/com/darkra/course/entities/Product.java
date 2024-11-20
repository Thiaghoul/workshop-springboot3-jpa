package com.darkra.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

//class used to represent an entity of the database with the table "tb_product"
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    //attribute used as the primary key of the entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //annotation defines this attribute as auto increment
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    //it maps the categories attribute with a relation "ManyToMany" with the Category Entity
    @ManyToMany
    @JoinTable(name = "tb_product_category", //this creates a table that represents the ManyToMany relation
            joinColumns = @JoinColumn(name = "product_id"), // Column referencing the Product entity
    inverseJoinColumns = @JoinColumn(name = "category_id")) // Column referencing the Category entity
    private Set<Category> categories = new HashSet<>();;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    //constructors
    public Product(){

    }

    public Product(Long id, String name, String description, Double price, String imgUrl){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @JsonIgnore
    public Set<Order> getOrders(){
        Set<Order> orders = new HashSet<>();
        for(OrderItem item : items){
            orders.add(item.getOrder());
        }
        return orders;
    }

    //hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
