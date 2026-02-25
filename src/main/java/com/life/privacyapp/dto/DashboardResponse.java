
package com.life.privacyapp.dto;

public class DashboardResponse {

    private String name;
    private int xp;
    private int totalLessons;
    private long unlockedLessons;

    public DashboardResponse(String name, int xp, int totalLessons, long unlockedLessons) {
        this.name = name;
        this.xp = xp;
        this.totalLessons = totalLessons;
        this.unlockedLessons = unlockedLessons;
    }

    public String getName() { return name; }
    public int getXp() { return xp; }
    public int getTotalLessons() { return totalLessons; }
    public long getUnlockedLessons() { return unlockedLessons; }
}