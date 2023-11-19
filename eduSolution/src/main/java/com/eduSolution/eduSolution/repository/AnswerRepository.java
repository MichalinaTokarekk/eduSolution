package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.HTFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer> findByHomeworkTestId (int id);
    List<Answer> findByUserId (int id);

    Answer findByHomeworkTestIdAndUserId(int homeworkTestId, int userId);


    @Query("SELECT a FROM Answer a " +
            "INNER JOIN a.user u " +
            "WHERE a.homeworkTest.id = :homeworkTestId ")
    List<Answer> findByHomeworkTest(@Param("homeworkTestId") int homeworkTestId);
}
