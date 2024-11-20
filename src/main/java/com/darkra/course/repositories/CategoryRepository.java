package com.darkra.course.repositories;

import com.darkra.course.entities.Category;
import com.darkra.course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Long id(Long id);
}
