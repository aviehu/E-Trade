package com.workshop.ETrade.Domain.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isToday(){
        LocalDate today = LocalDate.now();

        int currDay = today.getDayOfYear();
        int currMonth = today.getMonthValue();
        int currYear = today.getYear();

        return currYear == year && currMonth == month && currDay == day;
    }
}
