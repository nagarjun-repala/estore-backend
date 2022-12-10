package com.nagarjun.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    
}
