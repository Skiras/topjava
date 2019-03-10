package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetween(T comparable, T start, T end) {
        return comparable.compareTo(start) >= 0 && comparable.compareTo(end) <= 0;
    }

    public static LocalDate parseLocalDate(String localDate, LocalDate def) {
        return StringUtils.isEmpty(localDate) ? def : LocalDate.parse(localDate);
    }

    public static LocalTime parseLocalTime(String localDate, LocalTime def) {
        return StringUtils.isEmpty(localDate) ? def : LocalTime.parse(localDate);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
