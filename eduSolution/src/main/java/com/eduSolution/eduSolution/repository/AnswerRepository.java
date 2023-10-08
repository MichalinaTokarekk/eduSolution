package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.HTFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer> findByHomeworkTestId (int id);
    List<Answer> findByUserId (int id);
}
