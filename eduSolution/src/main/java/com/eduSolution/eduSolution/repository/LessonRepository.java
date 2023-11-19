package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
//    @Query("SELECT l FROM Lesson l WHERE l.classGroup.semester.id = :semesterId")
//    List<Lesson> findByClassGroupSemesterId(@Param("semesterId") Integer semesterId);


//    @Query("SELECT l FROM Lesson l WHERE l.classGroup.semester.id IN :semesterIds")
//    List<Lesson> findByClassGroupSemesterIds(@Param("semesterIds") List<Integer> semesterIds);



}
