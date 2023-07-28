package com.sip.demo.security_role_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

		@RequestMapping(path = "/testing",method = RequestMethod.GET)
	    @ResponseBody
	    public String test(){
	        System.out.println("hello testing............................");
	        return "hello success";
	    }
}
