package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Course findByName (String name);

    @Query("SELECT DISTINCT c FROM User u " +
            "JOIN u.classGroups cg " +
            "JOIN cg.course c " +
            "WHERE u.id = :userId")
    List<Course> findCoursesByUserId(@Param("userId") Integer userId);

}
