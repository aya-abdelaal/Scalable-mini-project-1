package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart>{
    //The Dependency Injection Variables
    CartRepository cartRepository;
    //The Constructor with the requried variables mapping the Dependency Injection.
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public Cart addCart(Cart cart){
        return cartRepository.addCart(cart);
    }
    public ArrayList<Cart> getCarts(){
        return cartRepository.getCarts();
    }
    public Cart getCartById(UUID cartId){
        Cart cart =  cartRepository.getCartById(cartId);
        if(cart == null){

            throw new IllegalArgumentException("Cart not found");

        }
        return cart;
    }
    public Cart getCartByUserId(UUID userId){
        return cartRepository.getCartByUserId(userId);
    }
    public void addProductToCart(UUID cartId, Product product){
        Cart cart = cartRepository.getCartById(cartId);
        if(cart == null)
            throw new IllegalArgumentException("Cart not found");
        if(product == null)
            throw new IllegalArgumentException("Product is null");
        cartRepository.addProductToCart(cartId, product);
    }
    public void deleteProductFromCart(UUID cartId, Product product){
        Cart userCart = getCartById(cartId);
        if(userCart == null){
            throw new IllegalArgumentException("Cart is empty");
        }
        cartRepository.deleteProductFromCart(cartId, product);
    }
    public void deleteCartById(UUID cartId){

        Cart cart =  cartRepository.getCartById(cartId);
        if(cart == null){
            throw new IllegalArgumentException("Cart not found");
        }
        cartRepository.deleteCartById(cartId);
    }
}