package com.life.privacyapp.dto;

public class LessonResponse {

    private Long id;
    private String title;
    private int lessonOrder;
    private boolean unlocked;

    public LessonResponse(Long id, String title, int lessonOrder, boolean unlocked) {
        this.id = id;
        this.title = title;
        this.lessonOrder = lessonOrder;
        this.unlocked = unlocked;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public int getLessonOrder() { return lessonOrder; }
    public boolean isUnlocked() { return unlocked; }
}