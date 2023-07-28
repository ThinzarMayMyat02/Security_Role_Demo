package com.sip.demo.security_role_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sip.demo.security_role_demo.model.User;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    public User getUserByUsername(@Param("username") String username);

    public List<User> findByUsernameContainingIgnoreCase(String keyword);

    public Optional<User> findOneByUsername(String username);

//    @Query("UPDATE User u SET u.enabled=:enabled, u.roleSet=:roleSet where u.id=:id")
//     @Modifying(flushAutomatically  = true, clearAutomatically  = true)
//     public void updateEnabledAndRoles(int id,Boolean enabled,Set<Role> roleSet);

    @Query("UPDATE User u SET u.password=:password where u.id=:id")
    @Modifying(flushAutomatically  = true, clearAutomatically  = true)
    public void upadatePassword(int id,String password);
    
}