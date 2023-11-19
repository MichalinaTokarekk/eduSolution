package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Role;
import com.eduSolution.eduSolution.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail (String email);

    Optional<User> findByUsername(String username);

//    @Query("SELECT u.teachingClassGroups FROM User u WHERE u.id = :userId")
//    Set<ClassGroup> findTeachingClassGroupsById(@Param("userId") Integer userId);
//
//    @Query("SELECT u FROM User u WHERE u.classGroup.id = :classGroup")
//    List<User> findUsersByClassGroupId(int classGroup);
//
//    @Query("SELECT DISTINCT c FROM ClassGroup cg " +
//            "JOIN cg.courses c " +
//            "WHERE cg.id IN (SELECT tcg.id FROM User u JOIN u.classGroup tcg WHERE u.id = :userId)")
//    List<Course> findCoursesByUserId(int userId);

    List<User> findByRole(Role role);

    @Query("SELECT u.classGroups FROM User u WHERE u.id = :userId")
    List<ClassGroup> findClassGroupsById(Integer userId);

    @Query("SELECT u FROM User u JOIN u.classGroups cg WHERE cg.id = :classGroupId")
    List<User> findUsersByClassGroupId(@Param("classGroupId") Integer classGroupId);


}
