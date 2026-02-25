package com.life.privacyapp.repository;

import com.life.privacyapp.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findByLessonOrder(int lessonOrder);
}