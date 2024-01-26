package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.service.CourseService;
import com.eduSolution.eduSolution.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/course-controller")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/addCourse")
    public Course addCourse (@RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @PostMapping("/saveCourseWithImage")
    public Course saveCourseWithImage (@RequestParam (name = "imageFile", required = false)MultipartFile imageFile, @ModelAttribute Course course){
        return courseService.saveCourseWithImage(course, imageFile);
    }
    @PostMapping("/addCourses")
    public List<Course> addCourses (@RequestBody List<Course> courses){
        return courseService.saveCourses(courses);
    }
    @GetMapping("/courses")
    public List<Course> findAllCourses() {
        return courseService.getCourses();
    }
    @GetMapping ("/course/{id}")
    public Course findCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
    @GetMapping ("/courseName/{name}")
    public Course findSemesterByTitle(@PathVariable String name) {
        return courseService.getCourseByName(name);
    }

//    @GetMapping("/findCoursesByUserId/{userId}")
//    public List<Course> findCoursesByUserId(@PathVariable int userId) {
//        return courseService.findCoursesByUserId(userId);
//    }
//
//    @GetMapping("/findCoursesByStudentId/{userId}")
//    public List<Course> findCoursesByStudentId(@PathVariable int userId) {
//        return courseService.findCoursesByStudentId(userId);
//    }

    @GetMapping("/findCoursesByUserId/{userId}")
    public List<Course> findCoursesByUserId(@PathVariable Integer userId) {
        return courseService.findCoursesByUserId(userId);
    }

//    @PutMapping("/updateCourse")
//    public Course updateCourse (@RequestBody Course course) {
//        return courseService.updateCourse(course);
//    }

    @PutMapping("/updateCourse")
    public Course updateCourse (@RequestParam (name ="imageFile", required = false)MultipartFile imageFile, @ModelAttribute Course course) {
        return courseService.updateCourse(course, imageFile);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public DeleteResponseDTO deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }
}
