package com.nagarjun.estorebackend.web.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.service.CartService;

@RestController
@RequestMapping("/admin/cart")
public class CartControllerAdmin {

    @Autowired
    private CartService cartService;

    @GetMapping("/cartitems/user/{userId}")
    public ResponseEntity<List<CartItem>> getUserCartItems(@PathVariable Long userId){

        return new ResponseEntity<>(cartService.getCartItems(userId), HttpStatus.OK);
    }
    
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId){

        return new ResponseEntity<>(cartService.getCart(cartId), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getUserCart(@PathVariable Long userId){

        return new ResponseEntity<>(cartService.getUserCart(userId), HttpStatus.OK);
    }            
    
}
