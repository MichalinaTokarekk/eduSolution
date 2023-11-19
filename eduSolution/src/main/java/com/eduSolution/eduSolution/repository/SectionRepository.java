package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {
    Section findByName (String name);

    List<Section> findByClassGroupId (int id);
}
