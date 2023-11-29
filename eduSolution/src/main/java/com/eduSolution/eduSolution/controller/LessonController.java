package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.entity.Lesson;
import com.eduSolution.eduSolution.entity.WeekDays;
import com.eduSolution.eduSolution.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/lesson-controller")
@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @PostMapping("/addLesson")
    public Lesson addLesson (@RequestBody Lesson lesson){
        System.out.println("Received lesson: " + lesson.toString());
        return lessonService.saveLesson(lesson);
    }
    @PostMapping("/addLessons")
    public List<Lesson> addLessons (@RequestBody List<Lesson> lessons){
        return lessonService.saveLessons(lessons);
    }
    @GetMapping("/lessons")
    public List<Lesson> findAllLessons() {
        return lessonService.getLessons();
    }
    @GetMapping ("/lesson/{id}")
    public Lesson findLessonById(@PathVariable int id) {
        return lessonService.getLessonById(id);
    }


//    @GetMapping("/findByClassGroupOrTeachingClassGroups/{userId}")
//    public List<Lesson> findByClassGroupOrTeachingClassGroups(@PathVariable(name = "userId") Integer userId) {
//        return lessonService.findByClassGroupOrTeachingClassGroups(userId);
//    }

//    @GetMapping("/findByClassGroups/{userId}")
//    public List<Lesson> findByClassGroups(@PathVariable(name = "userId") Integer userId) {
//        return lessonService.findByClassGroups(userId);
//    }

    @GetMapping("/findLessonsForUserInClassGroups/{userId}")
    public List<Lesson> findLessonsForUserInClassGroups(@PathVariable Integer userId) {
        return lessonService.findLessonsForUserInClassGroups(userId);
    }

    @PutMapping("/updateLesson")
    public Lesson updateLesson (@RequestBody Lesson lesson) {
        return lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/deleteLesson/{id}")
    public String deleteLesson(@PathVariable int id) {
        return lessonService.deleteLesson(id);
    }

    @GetMapping("/weekDays")
    public List<String> getAllWeekDays() {
        return Arrays.stream(WeekDays.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
