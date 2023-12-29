package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Cart;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {
    List<Record> findByUserId (int id);
}
