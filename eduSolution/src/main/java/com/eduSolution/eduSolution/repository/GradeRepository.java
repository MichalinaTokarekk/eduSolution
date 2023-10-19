package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findByValue(double value);

//    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId")
//    List<Grade> findByStudentId(@Param("studentId") Integer studentId);

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.course.id = :courseId")
    List<Grade> findByStudentIdAndCourseId(Integer studentId, Integer courseId);

}
