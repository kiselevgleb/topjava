package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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

    @DeleteMapping("/meals")
    public String deleteMeals(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int id = Integer.parseInt(request.getParameter("id"));
        service.delete(id, userId);
        return "redirect:meals";
    }

    @PostMapping("/meals")
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
