package com.sip.demo.security_role_demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Product name must not be blank!")
    private String name;
    @NotBlank(message = "Product name must not be blank!")
    private String brand;
    @NotNull(message = "Price must not blank!")
    private int price;
    public Product() {
    }
    public Product(String name, String brand, int price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + "]";
    }
    
    
}
