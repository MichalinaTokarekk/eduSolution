package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/class-group-controller")
@RestController
public class ClassGroupController {
    @Autowired
    private ClassGroupService classGroupService;
    @PostMapping("/addGroup")
    public ClassGroup addGroup (@RequestBody ClassGroup group){
        return classGroupService.saveClassGroup(group);
    }
    @PostMapping("/addGroups")
    public List<ClassGroup> addGroups (@RequestBody List<ClassGroup> classGroups){
        return classGroupService.saveClassGroups(classGroups);
    }
    @GetMapping("/groups")
    public List<ClassGroup> findAllGroups() {
        return classGroupService.getClassGroups();
    }
    @GetMapping ("/group/{id}")
    public ClassGroup findGroupById(@PathVariable int id) {
        return classGroupService.getClassGroupById(id);
    }
    @GetMapping ("/groupName/{name}")
    public ClassGroup findGroupByTitle(@PathVariable String name) {
        return classGroupService.getClassGroupByName(name);
    }

    @GetMapping("/findClassGroupsByCourseId/{courseId}")
    public List<ClassGroup> findClassGroupsByCourseId(@PathVariable int courseId) {
        return (List<ClassGroup>) classGroupService.findClassGroupsByCoursesId(courseId);
    }

    @PutMapping("/updateGroup")
    public ClassGroup updateSemester (@RequestBody ClassGroup group) {
        return classGroupService.updateClassGroup(group);
    }

    @DeleteMapping("/deleteGroup/{id}")
    public DeleteResponseDTO deleteGroup(@PathVariable int id) {
        return classGroupService.deleteClassGroup(id);
    }
}
