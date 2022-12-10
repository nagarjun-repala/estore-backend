package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.Role;

public interface RoleService {

    Role getRoleById(Long roleId);
    Role createRole(Role role);
    Role updateRole(Long roleId, Role role);
    void delteRole(Long roleId);
    List<Role> getRoles();
}
