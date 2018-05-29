package com.gymtrackr.Domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseList {

    private List<Exercise> exercises;

    public ExerciseList() {
        exercises = new ArrayList<>();
    }

    public ExerciseList(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
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
