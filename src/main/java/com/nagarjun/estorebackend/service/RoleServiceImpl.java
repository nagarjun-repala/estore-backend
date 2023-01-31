package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.ResourceExistException;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
import com.nagarjun.estorebackend.exception.RoleNotFoundException;
import com.nagarjun.estorebackend.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

	@Autowired
	private UserService userService;

	@Override
	public Role getRoleById(Long roleId) {
        Optional<Role> orderEntity = roleRepository.findById(roleId);
        if(orderEntity.isEmpty()) throw new RoleNotFoundException(roleId);
        return orderEntity.get();
	}

	@Override
	public Role createRole(Role role) {
        return roleRepository.save(role);
	}

	@Override
	public Role updateRole(Long roleId, Role role) {
        Optional<Role> roleEntity = roleRepository.findById(roleId);
        if(roleEntity.isEmpty()) throw new RoleNotFoundException(roleId);
        Role updateRole = roleEntity.get();
        updateRole.setName(role.getName());
        return updateRole;
		
	}

	@Override
	public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
	}

	@Override
	public List<Role> getRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Role getRoleByName(String roleName) {
        Optional<Role> roleEntity = roleRepository.findByName(roleName);
        if(roleEntity.isEmpty()) throw new RoleNotFoundException(roleName);
		return roleEntity.get();
	}

	@Override
	public void assignRole(Long userId, String roleName) {
		User user = userService.getUser(userId);
		Set<Role> userRoles = user.getRoles();
		for (Role role : userRoles) {
			if(roleName.equals(role.getName())) throw new ResourceExistException("Role: " + roleName + " already assigned");
		}
		Role role = getRoleByName(roleName);
		role.getUsers().add(user);
		roleRepository.save(role);
	}

	@Override
	public void unassignRole(Long userId, String roleName) {
		User user = userService.getUser(userId);
		Set<Role> userRoles = user.getRoles();
		for (Role role : userRoles) {
			if(roleName.equals(role.getName())){
				role.getUsers().remove(user);
				roleRepository.save(role);
				return;
			}
		}
		throw new ResourceNotFoundException(roleName);
	}
}
