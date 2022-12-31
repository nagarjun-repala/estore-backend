package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.exception.RoleNotFoundException;
import com.nagarjun.estorebackend.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

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
		return (List<Role>) roleRepository.findAll();
	}

}
