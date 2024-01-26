package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public Course saveCourse (Course course){
        course.setCashAdvance(10);
        return courseRepository.save(course);
    }

//    public Course saveCourseWithImage(Course course, MultipartFile imageFile) {
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//            if (fileName.contains("..")) {
//                System.out.println("Not a valid file");
//            }
//            try {
//                course.setImage(Base64.getEncoder().encodeToString(imageFile.getBytes()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        return courseRepository.save(course);
//    }

    public Course saveCourseWithImage(Course course, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                course.setImage(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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

//    public List<Course> findCoursesByUserId(int userId) {
//        return courseRepository.findCoursesByUserId(userId);
//    }
//
//    public List<Course> findCoursesByStudentId(int userId) {
//        return userRepository.findCoursesByUserId(userId);
//    }

    public List<Course> findCoursesByUserId(Integer userId) {
        return courseRepository.findCoursesByUserId(userId);
    }

//    public Course updateCourse (Course course){
//        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
//        existingCourse.setName(course.getName());
//        existingCourse.setDescription(course.getDescription());
//        existingCourse.setAmountToPay(course.getAmountToPay());
//        existingCourse.setDifficultyLevel(course.getDifficultyLevel());
//        return courseRepository.save(existingCourse);
//    }

//    public Course updateCourse (Course course, MultipartFile imageFile){
//        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
//        if (existingCourse == null) {
//            // Obsłuż przypadek, gdy kurs nie istnieje
//            throw new EntityNotFoundException("Course with id " + course.getId() + " not found");
//        }
//        existingCourse.setName(course.getName());
//        existingCourse.setDescription(course.getDescription());
//        existingCourse.setAmountToPay(course.getAmountToPay());
//        existingCourse.setDifficultyLevel(course.getDifficultyLevel());
//        if (imageFile != null) {
//            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//            if (fileName.contains("..")) {
//                System.out.println("Not a valid file");
//            }
//            try {
//                existingCourse.setImage(Base64.getEncoder().encodeToString(imageFile.getBytes()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return courseRepository.save(existingCourse);
//    }

    public Course updateCourse(Course course, MultipartFile imageFile) {
        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
        if (existingCourse == null) {
            throw new EntityNotFoundException("Course with id " + course.getId() + " not found");
        }

        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setAmountToPay(course.getAmountToPay());
        existingCourse.setDifficultyLevel(course.getDifficultyLevel());

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                if (fileName.contains("..")) {
                    System.out.println("Not a valid file");
                } else {
                    existingCourse.setImage(imageFile.getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return courseRepository.save(existingCourse);
    }

    public Course updateCourseRemove(Course course, MultipartFile imageFile, boolean removeImage) {
        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
        if (existingCourse == null) {
            throw new EntityNotFoundException("Course with id " + course.getId() + " not found");
        }
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setAmountToPay(course.getAmountToPay());
        existingCourse.setDifficultyLevel(course.getDifficultyLevel());

        if (removeImage) {
            // Jeśli żądano usunięcia obrazu, ustaw wartość image na null
            existingCourse.setImage(null);
        } else if (imageFile != null) {
            // Jeśli przesłano nowy obraz, przetwórz go i ustaw w polu image
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Not a valid file");
            }
            try {
                existingCourse.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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
