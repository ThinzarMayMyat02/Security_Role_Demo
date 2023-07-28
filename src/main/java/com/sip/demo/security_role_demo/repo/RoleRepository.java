package com.sip.demo.security_role_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.demo.security_role_demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
    
}
