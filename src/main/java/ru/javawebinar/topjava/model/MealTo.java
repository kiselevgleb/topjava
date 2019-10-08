package ru.javawebinar.topjava.model;
import java.time.LocalDateTime;

//@Entity
//@Table (name = "mealTo")
public class MealTo {
//    @Column(name = "dateTime")
    private final LocalDateTime dateTime;
//    @Column(name = "description")
    private final String description;
//    @Column(name = "calories")
    private final int calories;

    private final boolean excess;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }
}
