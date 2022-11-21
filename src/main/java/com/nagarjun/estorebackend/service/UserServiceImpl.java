package com.nagarjun.estorebackend.service;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long userId) {

        Optional<User> userEntity = userRepository.findById(userId);
        return unwrapUser(userEntity, userId);

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

    @Override
    public void deleteUser(Long id) {
        
        Optional<User> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()){
            userRepository.deleteById(id);
        }
        else throw new EntityNotFoundException();
    }    

    static User unwrapUser(Optional<User> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();
    }
    

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> getUser = userRepository.findById(userId);

        User unwrappedUser = unwrapUser(getUser, userId);
        unwrappedUser.setFirstName(user.getFirstName());
        unwrappedUser.setLastName(user.getLastName());
        unwrappedUser.setEmail(user.getEmail());
        unwrappedUser.setPassword(user.getPassword());
        unwrappedUser.setUsername(user.getUsername());
        return unwrappedUser;
    }


}
