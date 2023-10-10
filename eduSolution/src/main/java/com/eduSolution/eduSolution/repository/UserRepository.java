package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail (String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT u.teachingClassGroups FROM User u WHERE u.id = :userId")
    Set<ClassGroup> findTeachingClassGroupsById(@Param("userId") Integer userId);
}
