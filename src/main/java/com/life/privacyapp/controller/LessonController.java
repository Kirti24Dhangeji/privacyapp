package com.life.privacyapp.controller;

import com.life.privacyapp.dto.LessonResponse;
import com.life.privacyapp.entity.Lesson;
import com.life.privacyapp.entity.User;
import com.life.privacyapp.repository.LessonRepository;
import com.life.privacyapp.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public LessonController(LessonRepository lessonRepository,
                            UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<LessonResponse> getLessons(@RequestParam Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        return lessonRepository.findAll()
                .stream()
                .map(lesson -> new LessonResponse(
                        lesson.getId(),
                        lesson.getTitle(),
                        lesson.getLessonOrder(),
                        user.getTotalXP() >= lesson.getRequiredXp()
                ))
                .collect(Collectors.toList());
    }
}