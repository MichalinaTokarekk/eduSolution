package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/semester-controller")
@RestController
public class SemesterController {
    @Autowired
    private SemesterService semesterService;
    @PostMapping("/addSemester")
    public Semester addSemester (@RequestBody Semester semester){
        return semesterService.saveSemester(semester);
    }
    @PostMapping("/addSemesters")
    public List<Semester> addSemesters (@RequestBody List<Semester> semesters){
        return semesterService.saveSemesters(semesters);
    }
    @GetMapping("/semesters")
    public List<Semester> findAllSemesters() {
        return semesterService.getSemesters();
    }
    @GetMapping ("/semester/{id}")
    public Semester findSemesterById(@PathVariable int id) {
        return semesterService.getSemesterById(id);
    }
    @GetMapping ("/semesterTitle/{title}")
    public Semester findSemesterByTitle(@PathVariable String name) {
        return semesterService.getSemesterByName(name);
    }

    @PutMapping("/updateSemester")
    public Semester updateSemester (@RequestBody Semester semester) {
        return semesterService.updateSemester(semester);
    }

    @DeleteMapping("/deleteSemester/{id}")
    public DeleteResponseDTO deleteSemester(@PathVariable int id) {
        return semesterService.deleteSemester(id);
    }
}
