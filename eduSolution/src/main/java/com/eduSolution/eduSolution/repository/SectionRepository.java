package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {
    Section findByName (String name);

    List<Section> findByClassGroupId (int id);

    @Query("SELECT s.name FROM Section s WHERE s.id = :id")
    String findNameById(@Param("id") int id);
}
