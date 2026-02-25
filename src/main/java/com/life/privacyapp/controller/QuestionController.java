package com.life.privacyapp.controller;

import com.life.privacyapp.entity.*;
import com.life.privacyapp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    @PostMapping("/{questionId}/answer")
    public ResponseEntity<String> answerQuestion(
            @PathVariable Long questionId,
            @RequestParam Long userId,
            @RequestParam String answer) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Prevent duplicate answer
        Optional<UserAnswer> existing =
                userAnswerRepository.findByUser_IdAndQuestion_Id(userId, questionId);

        if (existing.isPresent()) {
            return ResponseEntity.badRequest()
                    .body("You already answered this question!");
        }

        // Save answer
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user);
        userAnswer.setQuestion(question);
        userAnswer.setSelectedAnswer(answer);

        boolean isCorrect = answer.equalsIgnoreCase(question.getCorrectAnswer());
        userAnswer.setIsCorrect(isCorrect);
        userAnswerRepository.save(userAnswer);

        Long lessonId = question.getLesson().getId();

        UserProgress progress = userProgressRepository
                .findByUser_IdAndLesson_Id(userId, lessonId)
                .orElseGet(() -> {
                    UserProgress up = new UserProgress();
                    up.setUser(user);
                    up.setLesson(question.getLesson());
                    return up;
                });

        if (isCorrect) {
            progress.setXpEarned(progress.getXpEarned() + 10);
        }

        int totalQuestions = questionRepository.findByLessonId(lessonId).size();
        int answeredQuestions =
                userAnswerRepository.countByUser_IdAndQuestion_Lesson_Id(userId, lessonId);

        if (answeredQuestions == totalQuestions) {
            progress.setCompleted(true);

            // FR-17: Update totalXP upon lesson completion
            user.setTotalXP(user.getTotalXP() + progress.getXpEarned());
            userRepository.save(user);
        }

        userProgressRepository.save(progress);

        return ResponseEntity.ok(isCorrect ? "Correct!" : "Incorrect!");
    }
}