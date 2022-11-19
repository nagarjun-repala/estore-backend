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
        return unwrappOrder(orderEntity, orderId);
    }

    @Override
    public Order createOrder(Order order, Long userId, Long productId) {

        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        Integer quantity = order.getQuantity();
        order.setCreatedOn(LocalDateTime.now());
        order.setProduct(product);
        order.setUsers(user);
        order.setQuantity(quantity);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);

        Order unwrappedOrder = unwrappOrder(orderEntity, orderId);

        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);

        if(orderEntity.isPresent()) orderRepository.deleteById(orderId);
        else throw new EntityNotFoundException();
 
    }

    @Override
    public List<Order> getOrders() {
        
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getOrderByUserIdAndProductId(Long userId, Long productId) {
        
        Optional<Order> order = orderRepository.findByUsersIdAndProductId(userId, productId);

        return unwrappOrder(order, userId);
    }    

    @Override
    public Order getOrderByUserId(Long userId) {
        
        Optional<Order> order = orderRepository.findByUsersId(userId);

        return unwrappOrder(order, userId);
    }       

    static Order unwrappOrder(Optional<Order> orderEntity, Long orderId) {
        if (orderEntity.isPresent()) return orderEntity.get();
        else throw new EntityNotFoundException();
    }

}
