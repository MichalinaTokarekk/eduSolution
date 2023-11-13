package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Lesson;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.LessonRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

    public Lesson saveLesson (Lesson lesson){
        Course course = lesson.getCourse();
        if(course != null) {
            course = courseRepository.findById(course.getId()).orElse(null);
            lesson.setCourse(course);
        }

        ClassGroup classGroup = lesson.getClassGroup();
        if(classGroup != null) {
            classGroup = classGroupRepository.findById(classGroup.getId()).orElse(null);
            lesson.setClassGroup(classGroup);
        }


        return lessonRepository.save(lesson);
    }

    public List<Lesson> saveLessons (List <Lesson> lessons){
        return lessonRepository.saveAll(lessons);
    }
    public  Lesson getLessonById (int id){
        return lessonRepository.findById(id).orElse(null);
    }

    public  List<Lesson> getLessons (){
        return lessonRepository.findAll();
    }

    public List<Lesson> findByClassGroupOrTeachingClassGroups(Integer groupId, Integer userId) {
        return lessonRepository.findByClassGroupOrTeachingClassGroups(groupId, userId);
    }


    public Lesson updateLesson (Lesson lesson){
        Lesson existingLesson = lessonRepository.findById(lesson.getId()).orElse(null);
        existingLesson.setCourse(lesson.getCourse());
        existingLesson.setClassGroup(lesson.getClassGroup());

        return lessonRepository.save(existingLesson);
    }

    public DeleteResponseDTO deleteLesson(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        lessonRepository.deleteById(id);
//        return "Grupa " + name + " został usunięty";
        return lesson != null ? new DeleteResponseDTO(lesson.getId(), lesson.getCourse().getName()) : null;
    }

}
