package ru.javawebinar.topjava.model;
import java.time.LocalDateTime;

public class UserMealWithExceed {
    private final LocalDateTime dateTime;
//    @Column(name = "description")
    private final String description;
//    @Column(name = "calories")
    private final int calories;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }
}
