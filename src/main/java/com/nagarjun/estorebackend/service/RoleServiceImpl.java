package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.RoleNotFoundException;
import com.nagarjun.estorebackend.repository.RoleRepository;
import com.nagarjun.estorebackend.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

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
        Optional<Role> orderEntity = roleRepository.findById(roleId);
        if(orderEntity.isEmpty()) throw new RoleNotFoundException(roleId);
        Role updateRole = orderEntity.get();
        updateRole.setName(role.getName());
        return updateRole;
		
	}

	@Override
	public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Role getRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(roleName).get();
	}

	@Override
	public Role assignRole(Long userId, Role roleName) {
        // TODO Auto-generated method stub
		User user = userRepository.findById(userId).get();
		Role role = roleRepository.findByName(roleName.getName()).get();
		role.getUsers().add(user);
		roleRepository.save(role);
		return role;
	}

	@Override
	public void unassignRole(Long userId, String roleName) {
        // TODO Auto-generated method stub
		User user = userRepository.findById(userId).get();
		Role role = roleRepository.findByName(roleName).get();
		role.getUsers().remove(user);
		roleRepository.save(role);
	}

}
