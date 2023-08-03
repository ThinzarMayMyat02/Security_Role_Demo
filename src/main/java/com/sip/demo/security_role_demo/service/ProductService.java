package com.sip.demo.security_role_demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sip.demo.security_role_demo.model.Product;

@Service
public interface ProductService {

    public List<Product> listAllProducts();

    public Product saveProduct(Product product);

    public void deleteProduct(int id);

    public Product get(int id);

    public List<Product> findByBrandContainingIgnoreCase(String keyword);

    public List<Product> findByNameContainingIgnoreCase(String keyword);
}