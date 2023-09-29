package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.EMFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EMFileRepository extends JpaRepository<EMFile,Integer> {
    EMFile findByName (String name);
}
