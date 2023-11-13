package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Lesson;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.service.LessonService;
import com.eduSolution.eduSolution.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findByClassGroupOrTeachingClassGroups/{groupId}/{userId}")
    public List<Lesson> findByClassGroupOrTeachingClassGroups(@PathVariable(name = "groupId") Integer groupId, @PathVariable(name = "userId") Integer userId) {
        return lessonService.findByClassGroupOrTeachingClassGroups(groupId, userId);
    }

    @PutMapping("/updateLesson")
    public Lesson updateLesson (@RequestBody Lesson lesson) {
        return lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/deleteLesson/{id}")
    public DeleteResponseDTO deleteLesson(@PathVariable int id) {
        return lessonService.deleteLesson(id);
    }
}
