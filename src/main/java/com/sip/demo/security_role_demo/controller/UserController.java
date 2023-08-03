package com.sip.demo.security_role_demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.demo.security_role_demo.exception.UsernameAlreadyExistsException;
import com.sip.demo.security_role_demo.model.ChgPasswordForm;
import com.sip.demo.security_role_demo.model.User;
import com.sip.demo.security_role_demo.service.UserService;

@Controller
@RequestMapping(path = "/admin")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(path = "/listUser")
    public String listUsers(Model model, @Param("keyword") String keyword) {
        try {
            List<User> users = new ArrayList<User>();

            if (keyword == null) {
                userService.listAllUsers().forEach(users::add);
            } else {
                userService.findByUsernameContainingIgnoreCase(keyword).forEach(users::add);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("listUsers", users);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "list_users";
    }

    @RequestMapping(path = "/newUser")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRoles());
        return "user_form";
    }

    @RequestMapping(path = "/saveUser")
    public String saveUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", userService.getAllRoles());
            return "user_form";
        }
        try {
            userService.save(user);
            return "redirect:/admin/listUser";
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            model.addAttribute("roles", userService.getAllRoles());
            return "user_form";
        }
    }

    @RequestMapping(path = "/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRoles());
        return "edit_user";
    }

    @RequestMapping(path = "/updateUser/{id}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        try {
            userService.updateUser(id, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/listUser";

    }

    @RequestMapping(path = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/listUser";
    }

    @RequestMapping(path = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody ChgPasswordForm chgPasswordForm, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        if (passwordEncoder.matches(chgPasswordForm.getCurrentPassword(), user.getPassword())) {
            userService.updatePassword(chgPasswordForm,user);
            return ResponseEntity.ok().body("Successfully changed password!");
        } else {
            return ResponseEntity.internalServerError().body("Current password is wrong.");
        }
    }

    
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        System.out.println("user testing............................");
        return "success";
    }
}