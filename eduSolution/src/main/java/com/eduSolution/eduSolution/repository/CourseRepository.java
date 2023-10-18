package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Course findByName (String name);

    @Query(value = "SELECT * from course c INNER JOIN semesters_to_courses sc ON sc.course_id = c.id WHERE sc.semester_id = :id", nativeQuery = true)
    List<Course> findBySemesterId (int id);

//    @Query("SELECT cg.courses FROM ClassGroup cg WHERE cg.id = :classGroupId " +
//            "AND cg IN (SELECT tc FROM User u JOIN u.teachingClassGroups tc WHERE u.id = :userId)")
//    List<Course> findCoursesByClassGroupIdAndUserId(int classGroupId, int userId);

    @Query("SELECT DISTINCT c FROM ClassGroup cg " +
            "JOIN cg.courses c " +
            "WHERE cg.id IN (SELECT tcg.id FROM User u JOIN u.teachingClassGroups tcg WHERE u.id = :userId)")
    List<Course> findCoursesByUserId(int userId);
}
