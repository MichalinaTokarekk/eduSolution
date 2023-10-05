package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeworkTestRepository extends JpaRepository<HomeworkTest,Integer> {
    HomeworkTest findByName (String name);

    List<HomeworkTest> findBySectionId (int id);
}
