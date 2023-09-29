package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EduMaterialRepository extends JpaRepository<EduMaterial,Integer> {
    EduMaterial findByName (String name);
//    List<EduMaterial> findBySectionId (int id);
}
