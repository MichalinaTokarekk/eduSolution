package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import com.eduSolution.eduSolution.service.AnswerService;
import com.eduSolution.eduSolution.service.HomeworkTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/answer-controller")
@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @PostMapping("/addAnswer")
    public Answer addAnswer (@RequestBody Answer answer){
        return answerService.saveAnswer(answer);
    }

//    @PostMapping("/addSection")
//    public HomeworkTest addSection (@RequestBody HomeworkTest homeworkTest, @RequestParam("sectionId") int sectionId) {
//        return homeworkTestService.addSection(homeworkTest, sectionId);
//    }

    @PostMapping("/addAnswers")
    public List<Answer> addAnswers (@RequestBody List<Answer> answers){
        return answerService.saveAnswers(answers);
    }
    @GetMapping("/answers")
    public List<Answer> findAllAnswers() {
        return answerService.getAnswers();
    }
    @GetMapping ("/answer/{id}")
    public Answer findAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping (value = "/answersByHomeworkTestId/{homeworkTestId}")
    public List<Answer> findAnswersByHomeworkTestId(@PathVariable int homeworkTestId) {
        return answerService.getAnswersByHomeworkTestId(homeworkTestId);
    }

    @GetMapping (value = "/answersByUserId/{userId}")
    public List<Answer> findAnswersByUserId(@PathVariable int userId) {
        return answerService.getAnswersByUserId(userId);
    }

    @GetMapping(value = "/answerByHomeworkTestIdAndUserId/homeworkTest/{homeworkTestId}/user/{userId}")
    public Answer findByHomeworkTestIdAndUserId(@PathVariable int homeworkTestId, @PathVariable int userId) {
        return answerService.getAnswerByHomeworkTestIdAndUserId(homeworkTestId, userId);
    }

//    @GetMapping(value = "/findByClassGroupId/{classGroupId}")
//    public List<Answer> findByClassGroupId(@PathVariable int classGroupId) {
//        return answerService.findByClassGroupId(classGroupId);
//    }
//

    @GetMapping(value = "/findByHomeworkTest/{homeworkTestId}")
    public List<Answer> findByHomeworkTest(@PathVariable int homeworkTestId) {
        return answerService.findByHomeworkTest(homeworkTestId);
    }

    @PutMapping("/updateAnswer")
    public Answer updateAnswer (@RequestBody Answer answer) {
        return answerService.updateAnswer(answer);
    }

    @DeleteMapping("/deleteAnswer/{id}")
    public DeleteResponseDTO deleteAnswer(@PathVariable int id) {
        return answerService.deleteAnswer(id);
    }
}
