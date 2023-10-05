package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.HTFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HTFileRepository extends JpaRepository<HTFile,Integer> {
    Optional<HTFile> findByName (String fileName);

    List<HTFile> findByHomeworkTestId (int id);
}
