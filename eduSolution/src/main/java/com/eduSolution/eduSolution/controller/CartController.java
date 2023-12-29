package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.Cart;
import com.eduSolution.eduSolution.service.AnswerService;
import com.eduSolution.eduSolution.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart-controller")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("/addCart")
    public Cart addCart (@RequestBody Cart cart){
        return cartService.saveCart(cart);
    }
    @PostMapping("/addCarts")
    public List<Cart> addCarts (@RequestBody List<Cart> carts){
        return cartService.saveCarts(carts);
    }
    @GetMapping("/carts")
    public List<Cart> findAllCarts() {
        return cartService.getCarts();
    }
    @GetMapping ("/cart/{id}")
    public Cart findCartById(@PathVariable int id) {
        return cartService.getCartById(id);
    }
    @GetMapping (value = "/cartsByUserId/{userId}")
    public List<Cart> findCartsByUserId(@PathVariable int userId) {
        return cartService.getCartsByUserId(userId);
    }
    @PutMapping("/updateCart")
    public Cart updateCart (@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }
    @DeleteMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id) {
        return cartService.deleteCart(id);
    }
}
