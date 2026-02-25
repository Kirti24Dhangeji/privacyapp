package com.life.privacyapp.repository;

import com.life.privacyapp.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    Optional<UserProgress> findByUser_IdAndLesson_Id(Long userId, Long lessonId);
}