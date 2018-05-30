package com.gymtrackr.Domain;

import java.util.ArrayList;

public class Routine {

    private String name;
    private ArrayList<Exercise> exercises;
    private ArrayList<String> muscleGroup;
    //0 = Not Set; 1 = Monday; ... ; 7 = Sunday;
    private int dayOfTheWeek;


    public Routine() {}

    public Routine(String name,int dayOfTheWeek) {
        // Creating a new routine without any exercise
        this.name = name;
        this.exercises = new ArrayList<>();
        this.muscleGroup = new ArrayList<>();
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<String> getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(ArrayList<String> muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public int getDayOfTheWeek() { return dayOfTheWeek; }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

}
