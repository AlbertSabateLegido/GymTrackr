package com.gymtrackr.Domain;


import java.util.ArrayList;
import java.util.Collections;

public class ExerciseList {

    private ArrayList<Exercise> exercises;

    public ExerciseList() {
        exercises = new ArrayList<>();
    }

    public ExerciseList(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise newExercise) {
        exercises.add(newExercise);
        Collections.sort(exercises, new Exercise.ExerciseNameComparator());
    }

    public Exercise get(int position) {
        return exercises.get(position);
    }

    public int size() {
        return exercises.size();
    }
}
