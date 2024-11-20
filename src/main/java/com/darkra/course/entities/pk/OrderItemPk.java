package com.darkra.course.entities.pk;

import com.darkra.course.entities.Order;
import com.darkra.course.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

//class used as a multi value primary key for the OrderItem table
@Embeddable
public class OrderItemPk implements Serializable {

    private static final long serialVersionUID = 1L;

    //It maps the order attribute as a foreign key with relation "ManyToOne"
    @ManyToOne
    @JoinColumn(name = "order_id") // defines the column in the table that will store the foreign key of the order
    private Order order;

    //It maps the product attribute as a foreign key with relation "ManyToOne"
    @ManyToOne
    @JoinColumn(name = "product_id") // defines the column in the table that will store the foreign key of the product
    private Product product;

    //The getters and setters methods
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    //the hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPk that = (OrderItemPk) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
