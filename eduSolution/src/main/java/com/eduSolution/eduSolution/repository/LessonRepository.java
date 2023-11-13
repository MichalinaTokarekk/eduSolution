package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
    @Query("SELECT l FROM Lesson l " +
            "JOIN l.classGroup cg " +
            "JOIN cg.semester cs " +
            "WHERE (:groupId IS NULL " +
            "        OR (cg.id = :groupId AND cs.id IN (SELECT s.id FROM User u JOIN u.classGroup c JOIN c.semester s WHERE u.id = :userId)) " +
            "        OR EXISTS (SELECT 1 FROM User u WHERE u.id = :userId AND l.classGroup = u.classGroup) " +
            "        OR EXISTS (SELECT 1 FROM User u JOIN u.teachingClassGroups tcg WHERE u.id = :userId AND l.classGroup = tcg))")
    List<Lesson> findByClassGroupOrTeachingClassGroups(@Param("groupId") Integer groupId, @Param("userId") Integer userId);







}
