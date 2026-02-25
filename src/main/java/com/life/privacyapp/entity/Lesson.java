package com.life.privacyapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "lesson_order")
    private int lessonOrder;

    @Column(name = "required_xp")   // ✅ maps to existing DB column
    private int requiredXp;

    private boolean completed = false;

    private boolean unlocked = false;
    private int requiredXP;

    // ========================
    // GETTERS & SETTERS
    // ========================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLessonOrder() {
        return lessonOrder;
    }

    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public int getRequiredXp() {      // ✅ THIS was missing
        return requiredXp;
    }

    public void setRequiredXp(int requiredXp) {   // ✅ THIS was missing
        this.requiredXp = requiredXp;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public int getRequiredXP() {
    return requiredXP;
}

public void setRequiredXP(int requiredXP) {
    this.requiredXP = requiredXP;
}
}