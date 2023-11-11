package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.HTFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer> findByHomeworkTestId (int id);
    List<Answer> findByUserId (int id);

    Answer findByHomeworkTestIdAndUserId(int homeworkTestId, int userId);

    @Query(value = "SELECT * FROM Answer a " +
            "INNER JOIN User u ON a.user_id = u.id " +
            "WHERE u.class_group_id = :classGroupId", nativeQuery = true)
    List<Answer> findByClassGroupId(@Param("classGroupId") int classGroupId);


    @Query("SELECT a FROM Answer a " +
            "INNER JOIN a.user u " +
            "WHERE a.homeworkTest.id = :homeworkTestId " +
            "AND u.classGroup.id = :classGroupId")
    List<Answer> findByHomeworkTestAndClassGroup(@Param("homeworkTestId") int homeworkTestId, @Param("classGroupId") int classGroupId);

    @Query("SELECT a FROM Answer a " +
            "INNER JOIN a.user u " +
            "WHERE a.homeworkTest.id = :homeworkTestId ")
    List<Answer> findByHomeworkTest(@Param("homeworkTestId") int homeworkTestId);
}
