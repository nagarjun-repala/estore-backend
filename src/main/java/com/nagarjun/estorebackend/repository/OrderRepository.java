package com.nagarjun.estorebackend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    
}
