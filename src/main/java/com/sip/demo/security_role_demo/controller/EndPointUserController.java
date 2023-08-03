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
import com.sip.demo.security_role_demo.model.User;
import com.sip.demo.security_role_demo.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController("/user_api")
public class EndPointUserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/list_Users",produces = "application/json")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @PostMapping(value = "/save_user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping(value = "user_product/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping(value = "deleteProduct/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        Map<String, Boolean> map = new LinkedHashMap<String, Boolean>();
        userService.delete(id);
        map.put("Deleted user id: " + id, Boolean.TRUE);
        return ResponseEntity.ok(map);
    }
}
