package com.nagarjun.estorebackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.OrderProductQuantity;

public interface OrderProductQuantityRepository extends CrudRepository<OrderProductQuantity, Long> {

    List<OrderProductQuantity> findByOrderId(Long orderId);

}
