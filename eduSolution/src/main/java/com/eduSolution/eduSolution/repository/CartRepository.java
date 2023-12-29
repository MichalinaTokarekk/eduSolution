package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.Cart;
import com.eduSolution.eduSolution.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findByUserId (int id);
}
