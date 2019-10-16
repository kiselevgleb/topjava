package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    public static final List<Meal> MEALS = Arrays.asList(
//            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static List<MealTo> getTos(List<User> users, Collection<Meal> meals, int caloriesPerDay) {
        return getFiltered(users, meals, caloriesPerDay, meal -> true);
    }

    public static List<MealTo> getFilteredTos(List<User> users, Collection<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime) {
        return getFiltered(users, meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime));
    }

    private static List<MealTo> getFiltered(List<User> users, Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {

        List<MealTo> mealToAll = new ArrayList<>();
        List<User> users1 = users;
        log.info("usersList");
        for (User u : users) {
            List<Meal> userId = meals.stream().filter(meal -> meal.getUserId() == u.getUserId()).collect(Collectors.toList());
            Map<LocalDate, Integer> caloriesSumByDate = userId.stream().collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));
            List<MealTo> mealTo = userId.stream().filter(filter).map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                    .collect(Collectors.toList());
            mealToAll.addAll(mealTo);
        }

        return mealToAll;
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getUserId(), meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

}