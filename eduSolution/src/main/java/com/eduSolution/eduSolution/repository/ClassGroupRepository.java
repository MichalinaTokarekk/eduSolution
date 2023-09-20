package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassGroupRepository extends JpaRepository<ClassGroup,Integer> {
    ClassGroup findByName (String name);
}
