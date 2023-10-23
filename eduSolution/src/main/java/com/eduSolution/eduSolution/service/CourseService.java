package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public Course saveCourse (Course course){
        return courseRepository.save(course);
    }

    public List<Course> saveCourses (List <Course> courses){
        return courseRepository.saveAll(courses);
    }
    public  Course getCourseById (int id){
        return courseRepository.findById(id).orElse(null);
    }

    public  List<Course> getCourses (){
        return courseRepository.findAll();
    }

    public  Course getCourseByName (String name){
        return courseRepository.findByName(name);
    }

    public List<Course> findCoursesByUserId(int userId) {
        return courseRepository.findCoursesByUserId(userId);
    }

    public List<Course> findCoursesByStudentId(int userId) {
        return userRepository.findCoursesByUserId(userId);
    }

    public Course updateCourse (Course course){
        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        return courseRepository.save(existingCourse);
    }

    public DeleteResponseDTO deleteCourse(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        Course course = courseRepository.findById(id).orElse(null);
        courseRepository.deleteById(id);
//        return "Kurs " + name + " został usunięty";
        return course != null ? new DeleteResponseDTO(course.getId(), course.getName()) : null;
    }
}
