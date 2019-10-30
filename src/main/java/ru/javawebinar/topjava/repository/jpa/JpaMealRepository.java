package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
//            Query query = em.createQuery("UPDATE Meal m SET m.dateTime=:date_time, m.calories=:calories, m.description=:description WHERE m.id=:id AND m.user.id=:user_id");
//            query.setParameter("date_time", meal.getDateTime()).setParameter("calories", meal.getCalories()).setParameter("description", meal.getDescription()).setParameter("id", 107000).setParameter("user_id", userId).executeUpdate();
            em.persist(meal);
            return meal;
        } else {
            Query query = em.createQuery("UPDATE Meal m SET m.dateTime=:date_time, m.calories=:calories, m.description=:description WHERE m.id=:id AND m.user.id=:user_id");
            query.setParameter("date_time", meal.getDateTime()).setParameter("calories", meal.getCalories()).setParameter("description", meal.getDescription()).setParameter("id", meal.getId()).setParameter("user_id", userId).executeUpdate();
            return meal;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Meal m = em.find(Meal.class, id);
        if (m.getUser().getId() == userId) {
            Query query = em.createQuery("DELETE FROM Meal u WHERE u.id=:id");
            return query.setParameter("id", id).executeUpdate() != 0;
        } else {
            throw new NotFoundException("");
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Meal m = em.find(Meal.class, id);
        if (m.getUser().getId() == userId) {
            return m;
        } else {
            throw new NotFoundException("");
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL, Meal.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    @Transactional
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN, Meal.class).setParameter("user_id", userId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }
}