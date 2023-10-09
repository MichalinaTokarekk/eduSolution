package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassGroupRepository extends JpaRepository<ClassGroup,Integer> {
    ClassGroup findByName (String name);

    List<ClassGroup> findClassGroupsByCoursesId(int courseId);
}
