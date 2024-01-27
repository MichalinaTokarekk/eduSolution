package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public ClassGroup saveClassGroupWithImage(ClassGroup classGroup, MultipartFile imageFile) {
        Semester semester = classGroup.getSemester();
        if(semester != null) {
            semester = semesterRepository.findById(semester.getId()).orElse(null);
            classGroup.setSemester(semester);
        }

        Course course = classGroup.getCourse();
        if(course != null) {
            course = courseRepository.findById(course.getId()).orElse(null);
            classGroup.setCourse(course);
        }
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                classGroup.setImage(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return classgroupRepository.save(classGroup);
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

    public List<ClassGroup> findByCourseId(Integer courseId) {
        return classgroupRepository.findByCourseId(courseId);
    }

//    public List<ClassGroup> findClassGroupsByCoursesId(int courseId) {
//        return (List<ClassGroup>) classgroupRepository.findClassGroupsByCoursesId(courseId);
//    }
//
//    public List<ClassGroup> findClassGroupsByCourseAndUserId(int courseId, int userId) {
//        return classgroupRepository.findClassGroupsByCourseAndUserId(courseId, userId);
//    }

    public List<ClassGroup> findClassGroupsByCourseAndUser(Integer courseId, Integer userId) {
        return classgroupRepository.findClassGroupsByCourseAndUser(courseId, userId);
    }

    public String findNameById(int id) {
        return classgroupRepository.findNameById(id);
    }

    public List<ClassGroup> findPendingClassGroupsByCourseId(Integer classGroupId) {
        return classgroupRepository.findPendingClassGroupsByCourseId(classGroupId);
    }

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

    public ClassGroup updateClassGroupWithImage(ClassGroup classGroup, MultipartFile imageFile) {
        ClassGroup existingClassGroup = classgroupRepository.findById(classGroup.getId()).orElse(null);
        if (existingClassGroup == null) {
            throw new EntityNotFoundException("Course with id " + classGroup.getId() + " not found");
        }

        existingClassGroup.setName(classGroup.getName());
        existingClassGroup.setDescription(classGroup.getDescription());
        existingClassGroup.setYear(classGroup.getYear());
        existingClassGroup.setAddress(classGroup.getAddress());
        existingClassGroup.setMode(classGroup.getMode());
        existingClassGroup.setStudentsLimit(classGroup.getStudentsLimit());
        if (classGroup.getSemester() != null && classGroup.getSemester().getId() != 0) {
            Semester existingSemester = semesterRepository.findById(classGroup.getSemester().getId()).orElse(null);
            existingClassGroup.setSemester(existingSemester);
        }

        if (classGroup.getCourse() != null && classGroup.getCourse().getId() != 0) {
            Course existingCourse = courseRepository.findById(classGroup.getCourse().getId()).orElse(null);
            existingClassGroup.setCourse(existingCourse);
        }



        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                if (fileName.contains("..")) {
                    System.out.println("Not a valid file");
                } else {
                    existingClassGroup.setImage(imageFile.getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return classgroupRepository.save(existingClassGroup);
    }

    public ClassGroup updateClassGroupRemove(ClassGroup classGroup, MultipartFile imageFile, boolean removeImage) {
        ClassGroup existingClassGroup = classgroupRepository.findById(classGroup.getId()).orElse(null);
        if (existingClassGroup == null) {
            throw new EntityNotFoundException("Course with id " + classGroup.getId() + " not found");
        }
        existingClassGroup.setName(classGroup.getName());
        existingClassGroup.setDescription(classGroup.getDescription());
        existingClassGroup.setYear(classGroup.getYear());
        existingClassGroup.setAddress(classGroup.getAddress());
        existingClassGroup.setMode(classGroup.getMode());
        existingClassGroup.setStudentsLimit(classGroup.getStudentsLimit());
        if (classGroup.getSemester() != null && classGroup.getSemester().getId() != 0) {
            Semester existingSemester = semesterRepository.findById(classGroup.getSemester().getId()).orElse(null);
            existingClassGroup.setSemester(existingSemester);
        }

        if (classGroup.getCourse() != null && classGroup.getCourse().getId() != 0) {
            Course existingCourse = courseRepository.findById(classGroup.getCourse().getId()).orElse(null);
            if (existingCourse == null) {
                throw new EntityNotFoundException("Course with id " + classGroup.getCourse().getId() + " not found");
            }
            existingClassGroup.setCourse(existingCourse);
        } else {
            existingClassGroup.setCourse(null);
        }



        if (removeImage) {
            // Jeśli żądano usunięcia obrazu, ustaw wartość image na null
            existingClassGroup.setImage(null);
        } else if (imageFile != null) {
            // Jeśli przesłano nowy obraz, przetwórz go i ustaw w polu image
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Not a valid file");
            }
            try {
                existingClassGroup.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return classgroupRepository.save(existingClassGroup);
    }

    public DeleteResponseDTO deleteClassGroup(int id){

        ClassGroup classGroup = classgroupRepository.findById(id).orElse(null);
        classgroupRepository.deleteById(id);
        return classGroup != null ? new DeleteResponseDTO(classGroup.getId(), classGroup.getName()) : null;
    }
}
