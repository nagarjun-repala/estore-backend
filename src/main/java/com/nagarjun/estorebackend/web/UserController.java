package com.nagarjun.estorebackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<HttpStatus> findById(@PathVariable Long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {

        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<HttpStatus> getUsers() {
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
}
