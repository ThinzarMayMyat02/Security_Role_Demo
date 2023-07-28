package com.sip.demo.security_role_demo.serviceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sip.demo.security_role_demo.exception.UsernameAlreadyExistsException;
import com.sip.demo.security_role_demo.model.ChgPasswordForm;
import com.sip.demo.security_role_demo.model.Role;
import com.sip.demo.security_role_demo.model.User;
import com.sip.demo.security_role_demo.repo.RoleRepository;
import com.sip.demo.security_role_demo.repo.UserRepository;
import com.sip.demo.security_role_demo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void save(User user) throws Exception {
        if (!userRepository.findOneByUsername(user.getUsername()).isPresent()) {
            String encoded = passwordEncoder.encode(user.getPassword());
            user.setPassword(encoded);
            userRepository.saveAndFlush(user);
        }else{
            throw new UsernameAlreadyExistsException("Username already exists!!");
        }
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listAllUsers() {
        List<User> listUsers = userRepository.findAll();
        return listUsers;
    }

    @Override
    public User findUser(int id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles = roleRepository.findAll();
        return allRoles;
    }

    @Override
    public List<User> findByUsernameContainingIgnoreCase(String keyword) {
    	List<User> usersList = userRepository.findByUsernameContainingIgnoreCase(keyword);
        return usersList;
    }

    @Override
    public void updateUser(int id,User user) {
        System.out.println("update in service impl");
        	User u=userRepository.findById(id).get();
            u.setEnabled(user.isEnabled());
            u.setRoleSet(user.getRoleSet());
            userRepository.save(u);
    }

    public User getUserByUsername(String name){
        User user=userRepository.getUserByUsername(name);
        return user;
    }

    @Override
    public void updatePassword(ChgPasswordForm chgPasswordForm, User user) {
        String encrypted=passwordEncoder.encode(chgPasswordForm.getNewPassword());
        userRepository.upadatePassword(user.getId(), encrypted);
    }

}