package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.*;
import com.eduSolution.eduSolution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;


    public Cart saveCart (Cart cart){
        cart.setClassGroup(classGroupRepository.findById(cart.getClassGroup().getId()).orElse(null));
        cart.setUser(userRepository.findById(cart.getUser().getId()).orElse(null));
        return cartRepository.save(cart);
    }

    public List<Cart> saveCarts (List <Cart> carts){
        return cartRepository.saveAll(carts);
    }
    public  Cart getCartById (int id){
        return cartRepository.findById(id).orElse(null);
    }

    public  List<Cart> getCarts (){
        return cartRepository.findAll();
    }

    public List<Cart> getCartsByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart updateCart (Cart cart){
        Cart existingCart = cartRepository.findById(cart.getId()).orElse(null);
        existingCart.setClassGroup(cart.getClassGroup());
        existingCart.setUser(cart.getUser());
        return cartRepository.save(existingCart);
    }

    public String deleteCart(int id){
        cartRepository.deleteById(id);
        return "Pozycja została usunięta";
    }
}
