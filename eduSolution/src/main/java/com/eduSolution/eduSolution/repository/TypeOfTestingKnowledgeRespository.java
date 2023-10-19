package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Grade;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import com.eduSolution.eduSolution.entity.TypeOfTestingKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfTestingKnowledgeRespository extends JpaRepository<TypeOfTestingKnowledge,Integer> {
    TypeOfTestingKnowledge findByName (String name);
}
