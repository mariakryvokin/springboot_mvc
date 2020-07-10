package com.kryvokin.onlineshop.service;

import com.kryvokin.onlineshop.model.Cart;
import com.kryvokin.onlineshop.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private Cart cart;

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public boolean addProductToCart(Product product, int amount){
        cart.getCart().put(product, amount);
        return cart.getCart().containsKey(product);
    }

    public Cart getCart(){
        return cart;
    }

}
