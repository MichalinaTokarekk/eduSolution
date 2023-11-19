package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Grade;
import com.eduSolution.eduSolution.entity.TypeOfTestingKnowledge;
import com.eduSolution.eduSolution.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findByValue(double value);

//    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId")
//    List<Grade> findByStudentId(@Param("studentId") Integer studentId);

//    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.classGroup.id = :classGroupId")
//    List<Grade> findByStudentIdAndClassGroupeId(Integer studentId, Integer classGroupId);
//
//    @Query("SELECT g.typeOfTestingKnowledge FROM Grade g WHERE g.id = :gradeId")
//    TypeOfTestingKnowledge findTypeOfTestingKnowledgeByGradeId(Long gradeId);
//
//    boolean existsByStudentAndIsFinalValue(User student, boolean isFinalValue);
//
//    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.classGroup.id = :classGroupId")
//    List<Grade> findAllByStudentAndClassGroup(Integer studentId, Integer classGroupId);
//
    @Query("SELECT g FROM Grade g WHERE g.answer.id = :answerId")
    Grade findByAnswerId(Integer answerId);
//
//    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId")
//    List<Grade> findAllByStudent(Integer studentId);


}
