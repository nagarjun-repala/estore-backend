package com.nagarjun.estorebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.dto.UserDto;
import com.nagarjun.estorebackend.entity.User;

@Service
public interface UserService {

    User getUser(Long userId);
    User getUser(String username);
    User createUser(User user);
    User updateUser(Long userId, User user);
    void deleteUser(Long id);
    void delteUser(String username);
    List<User> getAllUsers();
    UserDto getUserDto(String username);
    
}
