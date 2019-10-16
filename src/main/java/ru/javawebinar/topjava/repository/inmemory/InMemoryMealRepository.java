package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

//    {
//        MealsUtil.MEALS.forEach(this::save);
//    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(id).getUserId() == userId) {
            return repository.remove(id) != null;
        } else {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(id).getUserId() == userId) {
            return repository.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> userMeals = new ArrayList<>();
        if (repository.values().size()!=0){
            for (Meal userMeal : repository.values()) {
                if (userMeal.getUserId() == userId) {
                    userMeals.add(userMeal);
                }
//            else if (userMeal.getUserId() == 2){
//                userMeals.add(userMeal);
//            }
            }}
        Comparator<Meal> userInitialsComparator = Comparator.comparing(Meal::getDateTime);
        Collections.sort(userMeals, userInitialsComparator);
        return userMeals;
    }

}

