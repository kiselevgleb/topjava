package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_USER = Sort.by(Sort.Direction.ASC, "user_id");

    @Autowired
    private CrudMealRepository crudRepository;
    @Autowired
    private CrudUserRepository crudUserRepository;


    @Override
    public Meal save(Meal meal, int userId) {

        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        meal.setUser(crudUserRepository.getOne(userId));
            crudRepository.save(meal);
            return meal;
    }


    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id,userId);
    }

    @Override
    public Meal get(int id, int userId) {
        Meal m = crudRepository.findById(id).get();
        if (m.getUser().getId()==userId){
        return crudRepository.findById(id).orElse(null);}
        else {
            return null;
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll(SORT_USER);
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {
        return crudRepository.getBetweenInclusive(startDate,endDate,userId);
    }
}
