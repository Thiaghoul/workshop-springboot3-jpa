package com.darkra.course.repositories;

import com.darkra.course.entities.Product;
import com.darkra.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
