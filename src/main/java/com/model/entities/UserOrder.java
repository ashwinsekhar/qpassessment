package com.model.entities;

public class UserOrder {
    private Integer orderId;
    private Integer quantity;

    public UserOrder(Integer orderId, Integer quantity) {
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "UserOrder [orderId=" + orderId + ", quantity=" + quantity + "]";
    }
}
