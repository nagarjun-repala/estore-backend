package com.nagarjun.estorebackend.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
    List<Order> findByUserUsername(String username);

}
