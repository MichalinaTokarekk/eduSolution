package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
//    List<Lesson> findByClassGroup_IdIn(List<Integer> classGroupIds);

    @Query("SELECT l FROM Lesson l " +
            "JOIN l.classGroup cg " +
            "WHERE cg IN (SELECT cu.classGroups FROM User cu WHERE cu.id = :userId)")
    List<Lesson> findLessonsForUserInClassGroupsWithoutStatus(@Param("userId") Integer userId);

    @Query("SELECT l FROM Lesson l " +
            "JOIN l.classGroup cg " +
            "WHERE cg IN (SELECT cu.classGroups FROM User cu WHERE cu.id = :userId) " +
            "AND cg.classGroupStatus = 'WTRAKCIE'")
    List<Lesson> findLessonsForUserInClassGroups(@Param("userId") Integer userId);


//    @Query("SELECT l FROM Lesson l " +
//            "JOIN l.classGroup cg " +
//            "WHERE cg.id = :classGroupId")
//    List<Lesson> findLessonsByClassGroupId(@Param("classGroupId") Integer classGroupId);

    @Query("SELECT l FROM Lesson l WHERE l.classGroup.id = :classGroupId")
    List<Lesson> findLessonsByClassGroupId(@Param("classGroupId") Integer classGroupId);

}
