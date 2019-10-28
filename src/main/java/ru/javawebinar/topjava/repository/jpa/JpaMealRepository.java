package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository

public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.getUser().getId()==userId) {
            if (meal.isNew()) {
                em.persist(meal);
                return meal;
            } else {
                return em.merge(meal);
            }
        }
        else {
            throw new NotFoundException("");
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal m = em.find(Meal.class, id);
        if (m.getUser().getId()==userId) {
            em.remove(m);
            return true;
        }
        else {
            throw new NotFoundException("");
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Meal m = em.find(Meal.class, id);
        if (m.getUser().getId()==userId) {
            return m;
        }
        else {
            throw new NotFoundException("");
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL, Meal.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN, Meal.class).setParameter("user_id", userId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }
}