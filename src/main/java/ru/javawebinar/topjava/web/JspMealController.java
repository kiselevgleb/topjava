package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController {
    @Autowired
    private MealService service;

//    @GetMapping("/")
//    public String root() {
//        return "index";
//    }

    @GetMapping("/meals")
    public String getMeals(Model model) {
        model.addAttribute("meals", service.getAll(SecurityUtil.authUserId()));
        return "meals";
    }

    @GetMapping(value = "filter")
    public String getFilteredMeals(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", service.getBetweenDates(startDate, endDate,userId));
        return "meals";
    }

    @PostMapping("/meals/delete")
    public String deleteMeals(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int id = Integer.parseInt(request.getParameter("id"));
        service.delete(id, userId);
        return "redirect:meals";
    }

    @PostMapping("/meals/update")
    public String PostMeals(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (StringUtils.isEmpty(request.getParameter("id"))) {
            service.create(meal, userId);
        } else {
            service.update(meal, userId);
        }
        return "redirect:meals";
    }

}
