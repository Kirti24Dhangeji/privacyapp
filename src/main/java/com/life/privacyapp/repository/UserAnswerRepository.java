package com.life.privacyapp.repository;

import com.life.privacyapp.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    Optional<UserAnswer> findByUser_IdAndQuestion_Id(Long userId, Long questionId);

    int countByUser_IdAndQuestion_Lesson_Id(Long userId, Long lessonId);
}