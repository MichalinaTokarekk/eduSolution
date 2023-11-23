package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClassGroupService {
    @Autowired
    private ClassGroupRepository classgroupRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    public ClassGroup saveClassGroup (ClassGroup group){
        Semester semester = group.getSemester();
        if(semester != null) {
            semester = semesterRepository.findById(semester.getId()).orElse(null);
            group.setSemester(semester);
        }

        Course course = group.getCourse();
        if(course != null) {
            course = courseRepository.findById(course.getId()).orElse(null);
            group.setCourse(course);
        }

        return classgroupRepository.save(group);
    }

    public List<ClassGroup> saveClassGroups (List <ClassGroup> classGroup){
        return classgroupRepository.saveAll(classGroup);
    }
    public  ClassGroup getClassGroupById (int id){
        return classgroupRepository.findById(id).orElse(null);
    }

    public  List<ClassGroup> getClassGroups (){
        return classgroupRepository.findAll();
    }

    public  ClassGroup getClassGroupByName (String name){
        return classgroupRepository.findByName(name);
    }

//    public List<ClassGroup> findClassGroupsByCoursesId(int courseId) {
//        return (List<ClassGroup>) classgroupRepository.findClassGroupsByCoursesId(courseId);
//    }
//
//    public List<ClassGroup> findClassGroupsByCourseAndUserId(int courseId, int userId) {
//        return classgroupRepository.findClassGroupsByCourseAndUserId(courseId, userId);
//    }

    public ClassGroup updateClassGroup (ClassGroup classGroup){
        ClassGroup existingClassGroup = classgroupRepository.findById(classGroup.getId()).orElse(null);
        existingClassGroup.setName(classGroup.getName());
        existingClassGroup.setDescription(classGroup.getDescription());
        existingClassGroup.setYear(classGroup.getYear());
        existingClassGroup.setAddress(classGroup.getAddress());
        existingClassGroup.setMode(classGroup.getMode());
        existingClassGroup.setSemester(semesterRepository.findById(classGroup.getSemester().getId()).orElse(null));
        existingClassGroup.setCourse(courseRepository.findById(classGroup.getCourse().getId()).orElse(null));
        return classgroupRepository.save(existingClassGroup);
    }

    public DeleteResponseDTO deleteClassGroup(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        ClassGroup classGroup = classgroupRepository.findById(id).orElse(null);
        classgroupRepository.deleteById(id);
//        return "Grupa " + name + " został usunięty";
        return classGroup != null ? new DeleteResponseDTO(classGroup.getId(), classGroup.getName()) : null;
    }
}
