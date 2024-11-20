package com.darkra.course.entities.enums;

//Used to define an order status, each value has a corresponding int number(code)
public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    private OrderStatus(int code) {
        this.code = code;

    }

    //method that returns the int value of the enum
    public int getCode() {
        return code;
    }

    //method that returns the enum value of a corresponding number
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status code!");
    }
}
