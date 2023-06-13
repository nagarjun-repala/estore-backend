package com.nagarjun.estorebackend.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.dto.UserDto;
import com.nagarjun.estorebackend.dto.VerifyUser;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.security.manager.CustomPrincipal;
import com.nagarjun.estorebackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/profile")
    public ResponseEntity<UserDto> findByUsername(@AuthenticationPrincipal CustomPrincipal principal) {
        return new ResponseEntity<>(userService.getUserDto(principal.getUsername()), HttpStatus.OK);
    }
  
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User user) {

        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/verifyUser")
    public ResponseEntity<VerifyUser> verifyUser() {
        return new ResponseEntity<>(new VerifyUser("Valid user"), null, HttpStatus.OK);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {

        return new ResponseEntity<>(userService.updateUser(id, user),HttpStatus.OK);
    }
}
