package com.nagarjun.estorebackend.web;

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
    public ResponseEntity<CartItem> createAddress(@PathVariable Long cartId, @PathVariable Long productId, @RequestBody CartItem cartItem){

        return new ResponseEntity<>(cartService.addProduct(cartId, productId, cartItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<Cart> deleteProduct(@PathVariable Long cartId, @PathVariable Long productId){

        cartService.deleteProduct(cartId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{cartId}/product/{productId}")
    public ResponseEntity<Cart> getProductsByCartIdAndProductId(@PathVariable Long cartId, @PathVariable Long productId){

        return new ResponseEntity<>(cartService.getProductsByCartIdAndProductId(cartId, productId), HttpStatus.OK);
    }      
    
}
