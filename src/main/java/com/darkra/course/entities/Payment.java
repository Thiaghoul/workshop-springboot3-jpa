package com.darkra.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

//class used to represent an entity of the database with the table "tb_payment"
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    private static long serialVersionUID = 1L;

    //attribute used as the primary key of the entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //annotation defines this attribute as auto increment
    private Long id;

    //attribute for the moment of the payment
    private Instant moment;

    //"OneToOne" relation with Order, representing the Order for this payment
    @JsonIgnore // annotation used to make sure loop doesn't happen in the JSON serialization
    @OneToOne
    @MapsId //links the primary key of the payment to the primary key of the order
    private Order order;

    //constructors
    public Payment(){

    }

    public Payment(Long id, Instant moment, Order order){
        this.id = id;
        this.moment = moment;
        this.order =  order;
    }

    //getter and setters methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    //hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
