package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> filteredmealList = new ArrayList<>();

        for (UserMeal u : mealList) {
            LocalDate uDate = LocalDate.of(u.getDateTime().getYear(), u.getDateTime().getMonthValue(), u.getDateTime().getDayOfMonth());
            int calories = 0;
            for (UserMeal uCalories : mealList) {
                LocalDate uDateCalories = LocalDate.of(uCalories.getDateTime().getYear(), uCalories.getDateTime().getMonthValue(), uCalories.getDateTime().getDayOfMonth());
                if (uDate.compareTo(uDateCalories) == 0) {
                    calories += uCalories.getCalories();
                }
            }
            if (TimeUtil.isBetween(LocalTime.of(u.getDateTime().getHour(), u.getDateTime().getMinute()), startTime, endTime)) {
                filteredmealList.add(new UserMealWithExceed(u.getDateTime(), u.getDescription(), u.getCalories(), calories <= caloriesPerDay));
            }
        }
        return filteredmealList;
    }

}
