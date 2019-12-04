package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
//@RequestMapping(value = MealUlController.REST_URL1, produces = MediaType.APPLICATION_JSON_VALUE)

@RequestMapping("/ajax/meals")
public class MealUlController extends AbstractMealController {
//    static final String REST_URL1 = "/ajax/meals";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getAll() {
        List<MealTo> l = super.getAll();
//        Collections.reverse(l);
        return l;
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(
            @RequestParam Integer id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime dateTime,
            @RequestParam String description,
            @RequestParam int calories) {


        Meal meal = new Meal(dateTime, description, calories);
//        if (id.equals("id")) {
        super.create(meal);
//        }
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/filter")
    public List<MealTo> getBetween(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam  LocalDate startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam  LocalTime startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam  LocalDate endDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam  LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}