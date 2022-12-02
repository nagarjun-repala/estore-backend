package com.nagarjun.estorebackend.mockData;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nagarjun.estorebackend.GlobalMethods;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.repository.UserRepository;

@Component
public class UserData {
    
    @Autowired
    private UserRepository userRepository;

    private User user = new User("test1", "test1", "test1", "test1", "test1@outlook.com", GlobalMethods.dateTimeFormatter(LocalDateTime.now()));

    private String validUser = "{\"firstName\": \"test2\", \"lastName\": \"test2\", \"username\" : \"test2\", \"password\" : \"test2\", \"email\" : \"test2@outlook.com\"}";
    private String invalidUser = "{\"firstName\": \"test3\", \"lastName\": \"test3\", \"password\" : \"test2\", \"email\" : \"test2@outlook.com\"}";


    public void setup(){
        userRepository.save(user);
        
    }

    public void clear(){
        userRepository.deleteAll();
    }

    public User getUser() {
        return user;
    }

    public String insertValidUser() {
        return validUser;
    }

    public String insertInvalidUser() {
        return invalidUser;
    }

}
