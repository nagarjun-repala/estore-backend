package com.nagarjun.estorebackend.web.admin;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {

        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }    
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {

        return new ResponseEntity<>(userService.updateUser(id, user),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<User> deleteUserByUsername(@PathVariable String username) {
        userService.delteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
              
}
