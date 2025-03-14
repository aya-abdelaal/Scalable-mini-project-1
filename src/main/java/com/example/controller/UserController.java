package com.example.controller;

import com.example.model.Order;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
//The Dependency Injection Variables
    UserService userService;
//The Constructor with the requried variables mapping the Dependency Injection.
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/")
    public ArrayList<User> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable UUID userId){
        try {
            return userService.getOrdersByUserId(userId);
        }catch (IllegalArgumentException e){
            return  new ArrayList<>();

        }
    }

    @PostMapping("/{userId}/removeOrder")
    public String removeOrderFromUser(@PathVariable UUID userId, @RequestParam UUID orderId){
        try {
            userService.removeOrderFromUser(userId, orderId);
            return "Order removed successfully";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable UUID userId){
        try {
            userService.deleteUserById(userId);
            return "User deleted successfully";
        }catch(IllegalArgumentException e){
            return  e.getMessage();
        }
    }

    @PostMapping("/{userId}/checkout")
    public String addOrderToUser(@PathVariable UUID userId){
        try {
            userService.addOrderToUser(userId);
            return "Order added successfully";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/{userId}/emptyCart")
    public String emptyCart(@PathVariable UUID userId){
        try {
            userService.emptyCart(userId);
            return "Cart emptied successfully";
        }catch (IllegalArgumentException e){
            return  e.getMessage();
        }
    }

    @PutMapping("/addProductToCart")
    public String addProductToCart(@RequestParam UUID userId, @RequestParam UUID productId){
        try {
            return userService.addProductToCart(userId, productId);
        }catch (IllegalArgumentException e){
            return  e.getMessage();
        }
    }

    @PutMapping("/deleteProductFromCart")
    public String deleteProductFromCart(@RequestParam UUID userId, @RequestParam UUID productId){
        try{
        userService.deleteProductFromCart(userId, productId);
        return "Product deleted from cart";
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }





}