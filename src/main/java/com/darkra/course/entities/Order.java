package com.darkra.course.entities;

import com.darkra.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//class used to represent an entity of the database with the table "tb_order"
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    private static long serialVersionUID = 1L;

    //attribute used as the primary key of the entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //annotation defines this attribute as auto increment
    private Long id;

    //annotation formats the attribute for a better JSON serialization
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant moment;

    //it maps the client attribute as a foreign key with relation "ManyToOne"
    @ManyToOne
    @JoinColumn(name = "client_id") // defines the column in the table that will store the foreign key of the client
    private User client;

    //attribute to store the status of the order as an integer mapped to the OrderStatus enum
    private Integer orderStatus;

    //"OneToMany" relation with OrderItem, representing items in the order
    @OneToMany(mappedBy = "id.order") // Mapped by the "id.order" attribute in the OrderItem entity
    private Set<OrderItem> items = new HashSet<>();

    //"OneToOne" relation with Payment, representing the payment for this order
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    //constructors
    public Order() {

    }

    public Order(Long Id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    //getters and setters methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getUser() {
        return client;
    }

    public void setUser(User client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(this.orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){
            this.orderStatus = orderStatus.getCode();
        }
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment(){
        return payment;
    }

    public Double getTotal(){
        Double total = 0.0;
        for(OrderItem orderItem: this.items){
            total += orderItem.getSubTotal();
        }
        return total;
    }

    //hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
