package com.nagarjun.estorebackend.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> findRoleById(@PathVariable Long roleId) {

        return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createRole(@Valid @RequestBody Role role) {

        roleService.createRole(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Long roleId, @Valid @RequestBody Role role) {

        return new ResponseEntity<>(roleService.updateRole(roleId, role), HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Role>> getRoles() {
        
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Role> assignRole(@PathVariable Long userId, @Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.assignRole(userId, role), HttpStatus.OK);
    }
    
    @DeleteMapping("/{roleName}/user/{userId}")
    public ResponseEntity<HttpStatus> unassignRole(@PathVariable Long userId, @PathVariable String roleName) {
        roleService.unassignRole(userId, roleName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    

}
