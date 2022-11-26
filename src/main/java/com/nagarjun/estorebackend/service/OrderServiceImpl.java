package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.OrderNotFoundException;
import com.nagarjun.estorebackend.repository.OrderRepository;
import com.nagarjun.estorebackend.repository.ProductRepository;
import com.nagarjun.estorebackend.repository.UserRepository;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order getOrderById(Long orderId) {
        
        Optional<Order> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty()) throw new OrderNotFoundException(orderId);
        return unwrappOrder(orderEntity, orderId);
    }

    @Override
    public Order createOrder(Order order, Long userId, Long productId) {

        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        Integer quantity = order.getQuantity();
        LocalDateTime currentDateTime = LocalDateTime.now();
        order.setCreatedOn(currentDateTime);
        order.setUpdatedOn(currentDateTime);
        order.setProduct(product);
        order.setUser(user);
        order.setQuantity(quantity);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty()) throw new OrderNotFoundException(orderId);
        Order updateOrder = orderEntity.get();
        updateOrder.setUpdatedOn(LocalDateTime.now());
        updateOrder.setQuantity(order.getQuantity());
        return updateOrder;
        
    }

    @Override
    public void deleteOrder(Long orderId) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty()) throw new OrderNotFoundException(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrders() {
        
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getOrderByUserIdAndProductId(Long userId, Long productId) {
        
        Optional<Order> orderEntity = orderRepository.findByUserIdAndProductId(userId, productId);
        if(orderEntity.isEmpty()) throw new OrderNotFoundException(userId, productId);
        return orderEntity.get();
    }    

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        
        List<Order> orderEntity = orderRepository.findAllByUserId(userId);
        if(orderEntity.isEmpty()) throw new OrderNotFoundException(userId, "User");        
        return (List<Order>) orderEntity;
    }       

    static Order unwrappOrder(Optional<Order> orderEntity, Long orderId) {
        if (orderEntity.isPresent()) return orderEntity.get();
        else throw new EntityNotFoundException();
    }

}
