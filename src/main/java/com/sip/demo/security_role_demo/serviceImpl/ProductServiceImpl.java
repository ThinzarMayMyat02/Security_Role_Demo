package com.sip.demo.security_role_demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sip.demo.security_role_demo.model.Product;
import com.sip.demo.security_role_demo.repo.ProductRepository;
import com.sip.demo.security_role_demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAllProducts() {
    	List<Product> productList=productRepository.findAll();
         return productList;
    }

    @Override @Transactional
    public void saveProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product get(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findByBrandContainingIgnoreCase(String keyword) {
        List<Product> productList= productRepository.findByBrandContainingIgnoreCase(keyword);
        return productList;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
    }

	@Override
	public List<Product> findByNameContainingIgnoreCase(String keyword){
		List<Product> productList=productRepository.findByNameContainingIgnoreCase(keyword);
		return productList;
	}
    
    

    
    
    
    
    
    
}