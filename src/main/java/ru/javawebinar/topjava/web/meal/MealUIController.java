package ru.javawebinar.topjava.web.meal;

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
@RequestMapping("/ajax/meals")
public class MealUIController extends AbstractMealController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void add(@RequestParam("dateTime") LocalDateTime dateTime,
                    @RequestParam("description") String description,
                    @RequestParam("calories") Integer calories) {
        super.create(new Meal(dateTime, description, calories));
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getBetween(@RequestParam("startDate") LocalDate startDate,
                                   @RequestParam("endDate") LocalDate endDate,
                                   @RequestParam("startTime") LocalTime startTime,
                                   @RequestParam("endTime") LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
