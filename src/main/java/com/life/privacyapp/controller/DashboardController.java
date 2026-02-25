package com.life.privacyapp.controller;

import com.life.privacyapp.entity.Lesson;
import com.life.privacyapp.entity.User;
import com.life.privacyapp.repository.LessonRepository;
import com.life.privacyapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping
    public Map<String, Object> getDashboard(@RequestParam Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Lesson> lessons = lessonRepository.findAll();

        long unlockedCount = lessons.stream()
        .filter(lesson -> user.getTotalXP() >= lesson.getRequiredXp())
        .count();

        Map<String, Object> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("xp", user.getTotalXP());
        response.put("totalLessons", lessons.size());
        response.put("unlockedLessons", unlockedCount);

        return response;
    }
}
