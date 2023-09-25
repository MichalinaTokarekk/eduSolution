package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.*;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Semester saveSemester (Semester semester){
        Set<Course> courses = new HashSet<>();
        if(courses != null) {
            for (Course course : semester.getCourses()) {
                Iterable<Course> coursesById = courseRepository.findAllById(Collections.singleton(course.getId()));
                coursesById.forEach(courses::add);

            }
        }
        semester.setCourses(courses);
        return semesterRepository.save(semester);
    }

    public List<Semester> saveSemesters (List <Semester> genres){
        return semesterRepository.saveAll(genres);
    }
    public  Semester getSemesterById (int id){
        return semesterRepository.findById(id).orElse(null);
    }

    public  List<Semester> getSemesters (){
        return semesterRepository.findAll();
    }

    public  Semester getSemesterByName (String name){
        return semesterRepository.findByName(name);
    }

    public Semester updateSemester (Semester semester){
        Semester existingSemester = semesterRepository.findById(semester.getId()).orElse(null);
        existingSemester.setName(semester.getName());
        Set<Course> courses = new HashSet<>();
        for (Course course : semester.getCourses()) {
            Iterable<Course> coursesById = courseRepository.findAllById(Collections.singleton(course.getId()));
            coursesById.forEach(courses::add);
        }
        existingSemester.setCourses(courses);
        return semesterRepository.save(existingSemester);
    }

    public DeleteResponseDTO deleteSemester(int id){
        List<Course> coursesBySemesterId = courseRepository.findBySemesterId(id);
        if (!coursesBySemesterId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje kurs w tym semestrze");
        }
        Semester semester = semesterRepository.findById(id).orElse(null);
        semesterRepository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return semester != null ? new DeleteResponseDTO(semester.getId(), semester.getName()) : null;
    }
}
