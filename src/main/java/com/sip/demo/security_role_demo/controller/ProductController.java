package com.sip.demo.security_role_demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sip.demo.security_role_demo.model.Product;
import com.sip.demo.security_role_demo.service.ProductService;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String listProducts(Model model,@Param("keyword") String keyword){
    	 try {
             List<Product>listProducts=new ArrayList<Product>();

             if(keyword == null){
                 productService.listAllProducts().forEach(listProducts::add);
             }else{
                 productService.findByNameContainingIgnoreCase(keyword).forEach(listProducts::add);
                 productService.findByBrandContainingIgnoreCase(keyword).forEach(listProducts::add);
                 model.addAttribute("keyword", keyword);
             }
             model.addAttribute("listProducts", listProducts);
         } catch (Exception e) {
             model.addAttribute("message", e.getMessage());
         }
        return "list_products";
    }

    @RequestMapping("/new")
    public String createProduct(Model model){
        Product product=new Product();
        model.addAttribute("product", product);
        return "new_product_form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult result){
    	if(result.hasErrors()) {
    		return "new_product_form";
    	}
        productService.saveProduct(product);
        return"redirect:/";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id){
        ModelAndView mav=new ModelAndView("edit_product");
        Product product=productService.get(id);
        mav.addObject(product);
        return mav;
    }
    
    @RequestMapping(value = "/update/{id}")
    public String updateProduct(@ModelAttribute("product")Product product, @PathVariable("id")int id) {
		productService.saveProduct(product);
		return "redirect:/";
	}

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")int id){
        productService.deleteProduct(id);
        return "redirect:/";
    }
    
    @RequestMapping(path = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        System.out.println("product testing............................");
        return "success";
    }
}