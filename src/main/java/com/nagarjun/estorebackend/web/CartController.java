package com.nagarjun.estorebackend.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/product/{productId}")
    public ResponseEntity<CartItem> addProduct(@PathVariable Long cartId, @PathVariable Long productId, @RequestBody CartItem cartItem){

        return new ResponseEntity<>(cartService.addProduct(cartId, productId, cartItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<Cart> deleteProduct(@PathVariable Long cartId, @PathVariable Long productId){
        // TODO Auto-generated method stub 
        cartService.deleteProduct(cartId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/products/user/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId){

        return new ResponseEntity<>(cartService.getCartItems(userId), HttpStatus.OK);
    }
    
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId){

        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId){

        return new ResponseEntity<>(cartService.getCartByUserId(userId), HttpStatus.OK);
    }            
    
}
