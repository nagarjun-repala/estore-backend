package com.nagarjun.estorebackend.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

    Optional<Role> findByName(String roleName);
}
