package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.*;
import com.eduSolution.eduSolution.repository.AnswerRepository;
import com.eduSolution.eduSolution.repository.HomeworkTestRepository;
import com.eduSolution.eduSolution.repository.SectionRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private HomeworkTestRepository homeworkTestRepository;

    @Autowired
    private UserRepository userRepository;

    public Answer saveAnswer (Answer answer){
        answer.setHomeworkTest(homeworkTestRepository.findById(answer.getHomeworkTest().getId()).orElse(null));
        answer.setUser(userRepository.findById(answer.getUser().getId()).orElse(null));
        answer.setAnswerStatus(AnswerStatus.NIEOCENIONE);
        return answerRepository.save(answer);
    }

    public Answer addAnswer (Answer answer, int sectionId) {
        HomeworkTest homeworkTest = homeworkTestRepository.findById(sectionId).orElse(null);
        answer.setHomeworkTest((HomeworkTest) homeworkTest);
        User user = userRepository.findById(sectionId).orElse(null);
        answer.setUser((User) user);
        return answerRepository.save(answer);
    }

    public List<Answer> saveAnswers (List <Answer> answers){
        return answerRepository.saveAll(answers);
    }
    public  Answer getAnswerById (int id){
        return answerRepository.findById(id).orElse(null);
    }

    public  List<Answer> getAnswers (){
        return answerRepository.findAll();
    }

    public List<Answer> getAnswersByHomeworkTestId(int homeworkTestId) {
        return answerRepository.findByHomeworkTestId(homeworkTestId);
    }

    public List<Answer> getAnswersByUserId(int userId) {
        return answerRepository.findByUserId(userId);
    }

    public List<Answer> findByClassGroupId(int classGroupId) {
        return answerRepository.findByClassGroupId(classGroupId);
    }

    public List<Answer> findByHomeworkTestAndClassGroup(int homeworkTestId, int classGroupId) {
        return answerRepository.findByHomeworkTestAndClassGroup(homeworkTestId, classGroupId);
    }

    public List<Answer> findByHomeworkTest(int homeworkTestId) {
        return answerRepository.findByHomeworkTest(homeworkTestId);
    }

    public Answer getAnswerByHomeworkTestIdAndUserId(int homeworkTestId, int userId) {
        return answerRepository.findByHomeworkTestIdAndUserId(homeworkTestId, userId);
    }


    public Answer updateAnswer (Answer answer){
        Answer existingAnswer = answerRepository.findById(answer.getId()).orElse(null);
        existingAnswer.setAnswerContent(answer.getAnswerContent());
        existingAnswer.setComment(answer.getComment());
        existingAnswer.setAnswerStatus(answer.getAnswerStatus());
        return answerRepository.save(existingAnswer);
    }

    public DeleteResponseDTO deleteAnswer(int id){
//        List<Course> coursesBySemesterId = courseRepository.findBySemesterId(id);
//        if (!coursesBySemesterId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje kurs w tym semestrze");
//        }
        Answer answer = answerRepository.findById(id).orElse(null);
        answerRepository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return answer != null ? new DeleteResponseDTO(answer.getId(), answer.getAnswerContent()) : null;
    }
}
