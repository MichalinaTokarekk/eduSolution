package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Course findByName (String name);

    @Query(value = "SELECT * from course c INNER JOIN semesters_to_courses sc ON sc.course_id = c.id WHERE sc.semester_id = :id", nativeQuery = true)
    List<Course> findBySemesterId (int id);
}
