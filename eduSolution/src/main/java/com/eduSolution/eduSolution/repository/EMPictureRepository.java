package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.EMPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EMPictureRepository extends JpaRepository<EMPicture,Integer> {
    EMPicture findByName (String name);
}
