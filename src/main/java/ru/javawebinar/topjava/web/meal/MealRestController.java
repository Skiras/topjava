package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public void save(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("save {} for User {}", meal, userId);
        service.save(meal, userId);
    }

    public Meal get(int mealId) {
        int userId = SecurityUtil.authUserId();
        log.info("get meal {} for User {}", mealId, userId);
        return service.get(mealId, userId);
    }

    public void delete(int mealId) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for User {}", mealId, userId);
        service.delete(mealId, userId);
    }

    public void update(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} for User {}", meal, userId);
        service.update(meal, userId);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for User {}", userId);
        return MealsUtil.getFilteredWithExcess(service.getAll(userId),
                SecurityUtil.authUserCaloriesPerDay(),
                LocalTime.MIN,
                LocalTime.MAX);

    }

    public List<MealTo> getBetween(LocalDate startDate, LocalTime startTime,
                                   LocalDate endDate, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween {} and {} date {} and {} time for User {}",
                startDate, endDate, startTime, endTime, userId);
        return MealsUtil.getFilteredWithExcess(
                service.getBetween(startDate, endDate, userId),
                SecurityUtil.authUserCaloriesPerDay(),
                startTime,
                endTime);
    }
}