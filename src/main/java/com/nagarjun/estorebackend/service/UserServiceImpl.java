package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.UserNotFoundException;
import com.nagarjun.estorebackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        
        user.setCreatedOn(LocalDateTime.now());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        
        return (List<User>)userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        
        Optional<User> userEntity = userRepository.findById(id);
        if(userEntity.isEmpty()) throw new UserNotFoundException(id);
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
}
