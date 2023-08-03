package com.sip.demo.security_role_demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sip.demo.security_role_demo.model.Product;
import com.sip.demo.security_role_demo.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController("/product_api")
public class EndPointProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/list_products",produces = "application/json")
    public List<Product> listAllProducts(){
        return productService.listAllProducts();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping(value = "update_product/{id}")
   public ResponseEntity<?> updateProduct(@PathVariable("id")int proId,@RequestBody Product product){
        Product updateProduct=productService.get(proId);
        updateProduct.setName(product.getName());
        updateProduct.setBrand(product.getBrand());
        updateProduct.setPrice(product.getPrice());
        return ResponseEntity.ok(productService.saveProduct(updateProduct));
   }

   @DeleteMapping("deleteProuct/{id}")
   public ResponseEntity<?> deleteProduct(@PathVariable("id")int id){
        Map<String,Boolean> map=new LinkedHashMap<String,Boolean>();
        productService.deleteProduct(id);
        map.put("Deleted product id: "+id, Boolean.TRUE);
        return ResponseEntity.ok(map);
   }

}
