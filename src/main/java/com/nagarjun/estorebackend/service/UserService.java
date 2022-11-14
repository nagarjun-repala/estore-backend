package com.nagarjun.estorebackend.service;

import java.util.List;
import com.nagarjun.estorebackend.entity.User;

public interface UserService {

    User getUser(Long userId);
    User getUser(String username);
    User createUser(User user);
    User updateUser(Long userId, User user);
    // User updateUser(String username, User user);
    List<User> getAllUsers();
    
}
