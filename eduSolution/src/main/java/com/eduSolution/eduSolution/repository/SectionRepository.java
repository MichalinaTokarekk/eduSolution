package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Integer> {
    Section findByName (String name);
}
