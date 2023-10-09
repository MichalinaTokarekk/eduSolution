package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.AFile;
import com.eduSolution.eduSolution.entity.HTFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AFileRepository extends JpaRepository<AFile,Integer> {
    Optional<AFile> findByName (String fileName);

    List<AFile> findByAnswerId (int id);
}
