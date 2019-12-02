package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/meals")
public class MealUlController extends AbstractMealController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getAll() {
        return super.getAll();
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
//            @RequestParam @Nullable Integer id,
                               @RequestParam LocalDateTime dateTime,
                               @RequestParam String description,
                               @RequestParam int calories) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse(dateTime1, formatter);
        Meal meal = new Meal(dateTime, description, calories);
//        if (id.equals("id")) {
            super.create(meal);
//        }
    }
}