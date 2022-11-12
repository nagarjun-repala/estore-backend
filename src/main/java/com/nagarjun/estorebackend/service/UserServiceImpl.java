package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.Repository.UserRepository;
import com.nagarjun.estorebackend.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long userId) {

        Optional<User> userEntity = userRepository.findById(userId);
        User unwrappedUser = unwrapUser(userEntity, userId);
        unwrappedUser.setPassword("null");
        return unwrappedUser;
    }

    @Override
    public User getUser(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
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

    static User unwrapUser(Optional<User> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();
    }

}
