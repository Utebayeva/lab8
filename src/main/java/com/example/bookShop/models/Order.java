package com.example.bookShop.models;

import java.util.Objects;

public class Order {
    private int orderId;
    private int userId;
    private int productId;
    private String userLogin;
    private String productName;

    public Order(int orderId, int userId, int productId, String userLogin, String productName) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.userLogin = userLogin;
        this.productName = productName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() &&
                getUserId() == order.getUserId() &&
                getProductId() == order.getProductId() &&
                Objects.equals(getUserLogin(), order.getUserLogin()) &&
                Objects.equals(getProductName(), order.getProductName());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", userLogin='" + userLogin + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
