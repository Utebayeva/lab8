package com.example.bookShop.models;

import java.util.Objects;

public class Product {
    private int product_id;
    private String product_name;
    private double product_price;
    private String product_desc;
    private int product_quantity;

    public Product(int product_id, String product_name, String product_desc, int product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_quantity = product_quantity;
    }

    public Product(int product_id, String product_name, double product_price, String product_desc, int product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_desc = product_desc;
        this.product_quantity = product_quantity;
    }

    public Product(String product_name, double product_price, String product_desc, int product_quantity) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_desc = product_desc;
        this.product_quantity = product_quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProduct_id() == product.getProduct_id() &&
                getProduct_quantity() == product.getProduct_quantity() &&
                Objects.equals(getProduct_name(), product.getProduct_name()) &&
                Objects.equals(getProduct_desc(), product.getProduct_desc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct_id(), getProduct_name(), getProduct_desc(), getProduct_quantity());
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_desc='" + product_desc + '\'' +
                ", product_quantity=" + product_quantity +
                '}';
    }
}
