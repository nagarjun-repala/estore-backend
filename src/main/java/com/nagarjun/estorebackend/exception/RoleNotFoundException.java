package com.nagarjun.estorebackend.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(Long id) {
        super("Role id: '"  + id + "' does not exist in our records");
    }

    public RoleNotFoundException(String roleName) {
        super("Role name: '"  + roleName + "' does not exist in our records");
    }

}
