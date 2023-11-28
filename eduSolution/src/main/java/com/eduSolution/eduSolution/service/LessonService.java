package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.entity.*;
import com.eduSolution.eduSolution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

    @Autowired
    private UserRepository userRepository;

    public Lesson saveLesson (Lesson lesson){
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


//    public List<Lesson> findByClassGroupOrTeachingClassGroups(Integer userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        if (user.getTeachingClassGroups().isEmpty()) {
//            return lessonRepository.findByClassGroupSemesterId(user.getClassGroup().getSemester().getId());
//        }
//        return lessonRepository.findByClassGroupSemesterIds(user.getTeachingClassGroups().stream().map(classGroup -> classGroup.getSemester().getId()).collect(Collectors.toList()));
//    }

//    public List<Lesson> findByClassGroups(Integer userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        return lessonRepository.findByClassGroupSemesterIds(user.getClassGroups().stream().map(classGroup -> classGroup.getId()).collect(Collectors.toList()));
//    }

    public List<Lesson> findLessonsForUserInClassGroups(Integer userId) {
        return lessonRepository.findLessonsForUserInClassGroups(userId);
    }


    public Lesson updateLesson (Lesson lesson){
        Lesson existingLesson = lessonRepository.findById(lesson.getId()).orElse(null);
        existingLesson.setName(lesson.getName());
        existingLesson.setDates(lesson.getDates());
        existingLesson.setStartLessonTime(lesson.getStartLessonTime());
        existingLesson.setEndLessonTime(lesson.getEndLessonTime());
        existingLesson.setDayName(lesson.getDayName());
        existingLesson.setClassGroup(classGroupRepository.findById(lesson.getClassGroup().getId()).orElse(null));

        return lessonRepository.save(existingLesson);
    }

    public String deleteLesson(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        lessonRepository.deleteById(id);
        return "Lekcja z grupu" + lesson.getClassGroup() + "z dnia" + lesson.getDates() + " została usunięta";
//        return lesson != null ? new DeleteResponseDTO(lesson.getId(), lesson.getDate()) : null;
    }

}
