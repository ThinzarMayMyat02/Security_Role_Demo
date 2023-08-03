package com.sip.demo.security_role_demo.service;

import java.security.Principal;
import java.util.List;
import org.springframework.stereotype.Service;

import com.sip.demo.security_role_demo.model.ChgPasswordForm;
import com.sip.demo.security_role_demo.model.User;

@Service
public interface UserService {

    public User save(User user) throws Exception ;

    public List<User> listAllUsers();

    public User findUser(int id);

    public void delete(int id);

    public Object getAllRoles();

    public List<User> findByUsernameContainingIgnoreCase(String keyword);

    public User updateUser(int id,User user);

    public User getUserByUsername(String name);

    public void updatePassword(ChgPasswordForm chgPasswordForm, User user);

}