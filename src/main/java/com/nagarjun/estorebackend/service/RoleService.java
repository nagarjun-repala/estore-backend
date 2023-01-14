package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.Role;

public interface RoleService {

    Role getRoleById(Long roleId);
    Role getRoleByName(String roleName);
    Role assignRole(Long userId, Role role);
    void unassignRole(Long userId, String roleName);
    Role createRole(Role role);
    Role updateRole(Long roleId, Role role);
    void deleteRole(Long roleId);
    List<Role> getRoles();
}
