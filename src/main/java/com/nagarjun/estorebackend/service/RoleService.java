package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import com.nagarjun.estorebackend.entity.Role;

public interface RoleService {

    @PreAuthorize("hasRole('ADMIN')")
    Role getRoleById(Long roleId);

    @PreAuthorize("hasRole('ADMIN')")
    Role getRoleByName(String roleName);

    @PreAuthorize("hasRole('ADMIN')")
    void assignRole(Long userId, String roleName);

    @PreAuthorize("hasRole('ADMIN')")
    void unassignRole(Long userId, String roleName);

    @PreAuthorize("hasRole('ADMIN')")
    Role createRole(Role role);

    @PreAuthorize("hasRole('ADMIN')")
    Role updateRole(Long roleId, Role role);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteRole(Long roleId);

    @PreAuthorize("hasRole('ADMIN')")
    List<Role> getRoles();
}
