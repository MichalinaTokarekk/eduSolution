package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.Grade;
import com.eduSolution.eduSolution.entity.TypeOfTestingKnowledge;
import com.eduSolution.eduSolution.service.AnswerService;
import com.eduSolution.eduSolution.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/grade-controller")
@RestController
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @PostMapping("/addGrade")
    public Grade addGrade (@RequestBody Grade grade){
        return gradeService.saveGrade(grade);
    }

    @PostMapping("/addFinalGrade")
    public Grade addFinalGrade (@RequestBody Grade grade) {
        return gradeService.saveFinalGrade(grade);
    }

    @PostMapping("/addGrades")
    public List<Grade> addGrades (@RequestBody List<Grade> grades){
        return gradeService.saveGrades(grades);
    }
    @GetMapping("/grades")
    public List<Grade> findAllAnswers() {
        return gradeService.getGrades();
    }
    @GetMapping ("/grade/{id}")
    public Grade findGradeById(@PathVariable long id) {
        return gradeService.getGradeById(id);
    }

//    @GetMapping("/getGradesByStudentId/{studentId}")
//    public List<Grade> getGradesByStudentId(Integer studentId) {
//        return gradeService.getGradesByStudentId(studentId);
//    }

    @GetMapping("/findByStudentIdAndCourseId/{studentId}/{courseId}")
    public List<Grade> findByStudentIdAndCourseId(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        System.out.println("Received studentId: " + studentId);
        List<Grade> grades = gradeService.findByStudentIdAndCourseId(studentId, courseId);
        System.out.println("Number of grades found: " + grades.size());
        return grades;
    }

    @GetMapping("/findTypeOfTestingKnowledgeByGradeId/{gradeId}")
    public TypeOfTestingKnowledge findTypeOfTestingKnowledgeByGradeId(@PathVariable Long gradeId) {
        return gradeService.findTypeOfTestingKnowledgeByGradeId(gradeId);
    }


    @PutMapping("/updateGrade")
    public Grade updateAnswer (@RequestBody Grade grade) {
        return gradeService.updateGrade(grade);
    }

    @PutMapping("/updateFinalGrade")
    public Grade updateFinalAnswer (@RequestBody Grade grade) {
        return gradeService.updateFinalGrade(grade);
    }

    @DeleteMapping("/deleteGrade/{id}")
    public DeleteResponseLongDTO deleteAnswer(@PathVariable long id) {
        return gradeService.deleteGrade(id);
    }
}
