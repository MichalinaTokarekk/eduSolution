package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/findByCourseId/{courseId}")
    public List<ClassGroup> findByCourseId(@PathVariable Integer courseId) {
        return classGroupService.findByCourseId(courseId);
    }

//    @GetMapping("/findClassGroupsByCourseId/{courseId}")
//    public List<ClassGroup> findClassGroupsByCourseId(@PathVariable int courseId) {
//        return (List<ClassGroup>) classGroupService.findClassGroupsByCoursesId(courseId);
//    }
//
//    @GetMapping("/findClassGroupsByCourseAndUserId/{courseId}/{userId}")
//    public List<ClassGroup> findClassGroupsByCourseAndUserId(@PathVariable int courseId, @PathVariable int userId) {
//        return classGroupService.findClassGroupsByCourseAndUserId(courseId, userId);
//    }

    @GetMapping("/findClassGroupsByCourseAndUser/{courseId}/{userId}")
    public List<ClassGroup> findClassGroupsByCourseAndUser(@PathVariable Integer courseId, @PathVariable Integer userId) {
        return classGroupService.findClassGroupsByCourseAndUser(courseId, userId);
    }

    @GetMapping("/findNameById/{id}")
    public ResponseEntity<String> findNameById(@PathVariable Optional<Integer> id) {
        if (id.isPresent()) {
            String className = classGroupService.findNameById(id.get());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return new ResponseEntity<>(className, headers, HttpStatus.OK);
        } else {
            // Obsługa przypadku, gdy id nie jest dostarczane
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID is missing");
        }
    }

    @GetMapping("/findPendingClassGroupsByCourseId/{courseId}")
    public List<ClassGroup> findPendingClassGroupsByCourseId(@PathVariable Integer courseId) {
        return classGroupService.findPendingClassGroupsByCourseId(courseId);
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
