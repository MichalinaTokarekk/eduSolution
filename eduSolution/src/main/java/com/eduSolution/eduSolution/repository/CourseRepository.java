package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Course findByName (String name);
}
