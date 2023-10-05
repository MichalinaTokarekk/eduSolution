package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import com.eduSolution.eduSolution.service.EduMaterialService;
import com.eduSolution.eduSolution.service.HomeworkTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/homeworkTest-controller")
@RestController
public class HomeworkTestController {

    @Autowired
    private HomeworkTestService homeworkTestService;
    @PostMapping("/addHomeworkTest")
    public HomeworkTest addHomeworkTest (@RequestBody HomeworkTest homeworkTest){
        return homeworkTestService.saveHomeworkTest(homeworkTest);
    }

//    @PostMapping("/addSection")
//    public HomeworkTest addSection (@RequestBody HomeworkTest homeworkTest, @RequestParam("sectionId") int sectionId) {
//        return homeworkTestService.addSection(homeworkTest, sectionId);
//    }

    @PostMapping("/addHomeworkTests")
    public List<HomeworkTest> addHomeworkTests (@RequestBody List<HomeworkTest> homeworkTests){
        return homeworkTestService.saveHomeworkTests(homeworkTests);
    }
    @GetMapping("/homeworkTests")
    public List<HomeworkTest> findAllEduMaterials() {
        return homeworkTestService.getHomeworkTests();
    }
    @GetMapping ("/homeworkTest/{id}")
    public HomeworkTest findHomeworkTestById(@PathVariable int id) {
        return homeworkTestService.getHomeworkTestById(id);
    }
    @GetMapping ("/homeworkTestName/{name}")
    public HomeworkTest findHomeworkTestByName(@PathVariable String name) {
        return homeworkTestService.getHomeworkTestByName(name);
    }
    @GetMapping (value = "/homeworkTestsBySectionId/{sectionId}")
    public List<HomeworkTest> findEduMaterialsBySectionId(@PathVariable int sectionId) {
        return homeworkTestService.getHomeworkTestsBySectionId(sectionId);
    }

    @PutMapping("/updateHomeworkTest")
    public HomeworkTest updateEduMaterial (@RequestBody HomeworkTest homeworkTest) {
        return homeworkTestService.updateHomeworkTest(homeworkTest);
    }

    @DeleteMapping("/deleteHomeworkTest/{id}")
    public DeleteResponseDTO deleteHomeworkTest(@PathVariable int id) {
        return homeworkTestService.deleteHomeworkTest(id);
    }
}
