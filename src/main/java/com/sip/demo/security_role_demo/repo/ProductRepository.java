package com.sip.demo.security_role_demo.repo;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.demo.security_role_demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Transactional
    public List<Product> findByBrandContainingIgnoreCase(String keyword);
    
    @Transactional
    public List<Product> findByNameContainingIgnoreCase(String keyword);


}