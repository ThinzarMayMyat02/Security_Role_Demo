package com.sip.demo.security_role_demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sip.demo.security_role_demo.model.User;
import com.sip.demo.security_role_demo.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User usr= userRepo.getUserByUsername(username);

       if(usr== null || usr.isEnabled()==false){
        throw new UsernameNotFoundException("Username is not found !!!!");
       }
       return new MyUserDetails(usr);
    }
    
}
