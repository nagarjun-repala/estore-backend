package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.GlobalMethods;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.UserNotFoundException;
import com.nagarjun.estorebackend.repository.CartRepository;
import com.nagarjun.estorebackend.repository.RoleRepository;
import com.nagarjun.estorebackend.repository.UserRepository;
import com.nagarjun.estorebackend.security.SecurityConstants;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public User getUser(Long userId) {

        Optional<User> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty()) throw new UserNotFoundException(userId);
        return userEntity.get();
    }

    @Override
    public User getUser(String username) {

        Optional<User> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty()) throw new UserNotFoundException(username);
        return userEntity.get();
    }

    @Override
    public User createUser(User user) {
        
        Cart cart = new Cart();
        cart.setTotal(0);
        Role role = roleRepository.findById(SecurityConstants.DEFAULT_ROLE).get();    
        user.setCreatedOn(GlobalMethods.dateTimeFormatter(LocalDateTime.now()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        role.getUsers().add(user);
        User savedUser = userRepository.save(user);
        cart.setUser(savedUser);
        cartRepository.save(cart);
        roleRepository.save(role);
        return savedUser;

    }

    @Override
    public List<User> getAllUsers() {
        
        return (List<User>)userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }    

    @Override
    public User updateUser(Long userId, User user) {

        Optional<User> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty()) throw new UserNotFoundException(userId);    
        User updateUser = userEntity.get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setUsername(user.getUsername());
        return updateUser;
    }

    @Override
    public void delteUser(String username) {
        userRepository.deleteUserByUsername(username);
    }
}
