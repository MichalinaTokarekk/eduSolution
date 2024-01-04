package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution,Long> {
    Institution findByAddress (String address);
}
