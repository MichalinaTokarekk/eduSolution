package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester,Integer> {
    Semester findByName (String name);
}
