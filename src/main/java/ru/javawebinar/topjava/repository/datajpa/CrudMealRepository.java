package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
//    public Meal save(Meal meal, int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    public boolean delete(int id, int userId);

//    public Meal get(int id, int userId);

//    public List<Meal> getAll(int userId);

    @Modifying
    @Query("SELECT m FROM Meal m " + "WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId);
}
