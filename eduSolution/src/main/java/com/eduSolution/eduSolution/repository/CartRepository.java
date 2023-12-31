package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.Cart;
import com.eduSolution.eduSolution.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findByUserId (int id);

//    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cart c " +
//            "WHERE c.classGroup.id = :classGroupId AND c.user.id = :userId")
//    boolean existsByClassGroupAndUser(@Param("classGroupId") Integer classGroupId, @Param("userId") Integer userId);

    @Query("SELECT COUNT(c) FROM Cart c WHERE c.user.id = :userId")
    int countByUserId(@Param("userId") int userId);

    @Query("SELECT SUM(c.classGroup.course.amountToPay) FROM Cart c WHERE c.user.id = :userId")
    BigDecimal getTotalAmountForUser(@Param("userId") int userId);
}
