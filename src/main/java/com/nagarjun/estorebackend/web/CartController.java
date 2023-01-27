package com.nagarjun.estorebackend.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarjun.estorebackend.dto.CartDto;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.security.manager.CustomPrincipal;
import com.nagarjun.estorebackend.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<CartItem> addProduct(@AuthenticationPrincipal CustomPrincipal principal, @PathVariable Long productId, @RequestBody CartItem cartItem){

        return new ResponseEntity<>(cartService.addProduct(principal.getUsername(), productId, cartItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<Cart> deleteProduct(@AuthenticationPrincipal CustomPrincipal principal, @PathVariable Long productId){
        cartService.deleteCartItem(principal.getUsername(), productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/products/user/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId){

        return new ResponseEntity<>(cartService.getCartItems(userId), HttpStatus.OK);
    }
    
    @GetMapping("/cartDetails")
    public ResponseEntity<CartDto> getCart(@AuthenticationPrincipal CustomPrincipal principal){

        return new ResponseEntity<>(cartService.getCartDto(principal.getUsername()), HttpStatus.OK);
    }
    
}
