package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.UserNotFoundException;
import com.nagarjun.estorebackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long userId) {

        Optional<User> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()) return userEntity.get();
        throw new UserNotFoundException(userId);
    }

    @Override
    public User getUser(String username) {

        Optional<User> userEntity = userRepository.findByUsername(username);
        if(userEntity.isPresent()) return userEntity.get();
        throw new UserNotFoundException(username);
    }

    @Override
    public User createUser(User user) {
        
        user.setCreatedOn(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        
        return (List<User>)userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        
        Optional<User> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()) userRepository.deleteById(id);
        throw new UserNotFoundException(id);
    }    

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> userEntity = userRepository.findById(userId);

        if(userEntity.isPresent()){
            User updateUser = userEntity.get();
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            updateUser.setUsername(user.getUsername());
            return updateUser;
        }
        throw new UserNotFoundException(userId);   
    }
}
