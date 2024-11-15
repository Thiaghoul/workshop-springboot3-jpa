package com.darkra.course.repositories;

import com.darkra.course.entities.OrderItem;
import com.darkra.course.entities.User;
import com.darkra.course.entities.pk.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
