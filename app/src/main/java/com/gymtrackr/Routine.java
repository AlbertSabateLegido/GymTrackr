package com.gymtrackr;

import java.util.ArrayList;

public class Routine {

    private String name;
    private ArrayList<Exercise> exercises;
    private ArrayList<String> muscleGroup;
    private DayOfTheWeek dayOfTheWeek;

    public Routine(String name) {
        // Creating a new routine without any exercise
        this.name = name;
        this.exercises = new ArrayList<>();
        this.muscleGroup = new ArrayList<>();
        this.dayOfTheWeek = DayOfTheWeek.NONE;
    }
}
