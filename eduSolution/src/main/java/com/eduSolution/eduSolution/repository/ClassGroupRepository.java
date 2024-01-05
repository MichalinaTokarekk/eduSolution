package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ClassGroupRepository extends JpaRepository<ClassGroup,Integer> {
    ClassGroup findByName (String name);

//    @Query("SELECT DISTINCT cg FROM ClassGroup cg " +
//            "JOIN cg.courses c " +
//            "WHERE c.id = :courseId " +
//            "AND cg.id IN (SELECT tcg.id FROM User u JOIN u.teachingClassGroups tcg WHERE u.id = :userId)")
//    List<ClassGroup> findClassGroupsByCourseAndUserId(int courseId, int userId);

    List<ClassGroup> findByCourseId(Integer courseId);

    @Query("SELECT DISTINCT cg FROM ClassGroup cg " +
            "JOIN cg.course c " +
            "JOIN User u ON u.id = :userId " +
            "WHERE c.id = :courseId AND cg MEMBER OF u.classGroups")
    List<ClassGroup> findClassGroupsByCourseAndUser(@Param("courseId") Integer courseId, @Param("userId") Integer userId);

    @Query("SELECT cg.name FROM ClassGroup cg WHERE cg.id = :id")
    String findNameById(@Param("id") int id);
    ;















}
