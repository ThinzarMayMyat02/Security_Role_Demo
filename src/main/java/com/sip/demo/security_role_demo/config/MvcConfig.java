package com.sip.demo.security_role_demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.sip.demo.security_role_demo.service")
public class MvcConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/login").setViewName("login");
      registry.addViewController("/403").setViewName("403");
    }
    
    
}
