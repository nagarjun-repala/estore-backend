package com.nagarjun.estorebackend.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByUsername(String username);
    
}
