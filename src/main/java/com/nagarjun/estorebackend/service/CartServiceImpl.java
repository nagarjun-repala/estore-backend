package com.nagarjun.estorebackend.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.ProductCartItem;
import com.nagarjun.estorebackend.entity.ProductQuantity;
import com.nagarjun.estorebackend.repository.CartRepository;
import com.nagarjun.estorebackend.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart addProduct(Long cartId, Long productId, ProductQuantity productQuantity) {

        Cart cart = cartRepository.findById(cartId).get();
        Product product = productRepository.findById(productId).get();
        cart.getProduct().add(product);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteProduct(Long cartId, Long productId) {
        // cartRepository.deleteByCartIdAndProductId(cartId, productId);
        
    }

    @Override
    public List<ProductCartItem> getProducts(Long cartId) {

        // List<ProductCartItem> products = new ArrayList<ProductCartItem>(); 
        // List<CartDetails> cartItems = cartRepository.findAllByCartId(cartId).get();
        // if(cartItems.isEmpty()) throw new ProductNotFoundException(cartId, "Cart");
        // for (CartDetails rawItem : cartItems) {
        //     Product cartRawProduct = new ProductCartItem(rawItem.getProduct());
        //     ProductCartItem item = (ProductCartItem) cartRawProduct;
        //     item.setQuantity(rawItem.getQuantity());
            
        //     products.add(item);
        // }
        // return products;
        return null;
    }

    @Override
    public Cart getProductsByCartIdAndProductId(Long cartId, Long productId) {
        
        // return (Cart) cartRepository.findByCartIdAndProductId(cartId, productId);
        return null;
    }
    
}
