package com.gymtrackr.Domain;

import java.util.ArrayList;
import java.util.List;

public class Routine {

    private String name;
    private List<Exercise> exercises;
    //0 = Not Set; 1 = Monday; ... ; 7 = Sunday;
    private int dayOfTheWeek;


    public Routine() {}

    public Routine(String name,int dayOfTheWeek) {
        // Creating a new routine without any exercise
        this.name = name;
        this.exercises = new ArrayList<>();
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getDayOfTheWeek() { return dayOfTheWeek; }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

}
