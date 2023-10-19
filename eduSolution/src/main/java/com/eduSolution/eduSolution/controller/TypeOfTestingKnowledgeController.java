package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.TypeOfTestingKnowledge;
import com.eduSolution.eduSolution.service.CourseService;
import com.eduSolution.eduSolution.service.TypeOfTestingKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/typeOfTestingKnowledge-controller")
@RestController
public class TypeOfTestingKnowledgeController {
    @Autowired
    private TypeOfTestingKnowledgeService typeOfTestingKnowledgeService;
    @PostMapping("/addTypeOfTestingKnowledge")
    public TypeOfTestingKnowledge addCourse (@RequestBody TypeOfTestingKnowledge typeOfTestingKnowledge){
        return typeOfTestingKnowledgeService.saveTypeOfTestingKnowledge(typeOfTestingKnowledge);
    }
    @PostMapping("/addTypesOfTestingKnowledge")
    public List<TypeOfTestingKnowledge> addTypesOfTestingKnowledge (@RequestBody List<TypeOfTestingKnowledge> typesOfTestingKnowledge){
        return typeOfTestingKnowledgeService.saveTypesOfTestingKnowledge(typesOfTestingKnowledge);
    }
    @GetMapping("/typesOfTestingKnowledge")
    public List<TypeOfTestingKnowledge> findAllTypesOfTestingKnowledge() {
        return typeOfTestingKnowledgeService.getTypesOfTestingKnowledge();
    }
    @GetMapping ("/typeOfTestingKnowledge/{id}")
    public TypeOfTestingKnowledge findTypeOfTestingKnowledgeById(@PathVariable int id) {
        return typeOfTestingKnowledgeService.getTypeOfTestingKnowledgeById(id);
    }
    @GetMapping ("/typeOfTestingKnowledgeName/{name}")
    public TypeOfTestingKnowledge findTypeOfTestingKnowledgeByName(@PathVariable String name) {
        return typeOfTestingKnowledgeService.getTypeOfTestingKnowledgeByName(name);
    }

//    @GetMapping("/findCoursesByUserId/{userId}")
//    public List<Course> findCoursesByUserId(@PathVariable int userId) {
//        return courseService.findCoursesByUserId(userId);
//    }

    @PutMapping("/updateTypeOfTestingKnowledge")
    public TypeOfTestingKnowledge updateTypeOfTestingKnowledge (@RequestBody TypeOfTestingKnowledge typeOfTestingKnowledge) {
        return typeOfTestingKnowledgeService.updateTypeOfTestingKnowledge(typeOfTestingKnowledge);
    }

    @DeleteMapping("/deleteTypeOfTestingKnowledge/{id}")
    public DeleteResponseDTO deleteTypeOfTestingKnowledge(@PathVariable int id) {
        return typeOfTestingKnowledgeService.deleteTypeOfTestingKnowledge(id);
    }
}
