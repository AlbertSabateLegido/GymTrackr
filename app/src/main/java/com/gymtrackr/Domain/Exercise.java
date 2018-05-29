package com.gymtrackr.Domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise {

    private String name;
    private List<String> musclesInvolved;
    private int reps;
    private int series;

    public Exercise() {}

    public Exercise (String name) {
        this.name = name;
        musclesInvolved = new ArrayList<>();
        reps = 0;
        series = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMusclesInvolved() {
        return musclesInvolved;
    }

    public void setMusclesInvolved(ArrayList<String> musclesInvolved) {
        this.musclesInvolved = musclesInvolved;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }


    public static class ExerciseNameComparator implements Comparator<Exercise> {
        public int compare(Exercise left, Exercise right) {
            return left.getName().toLowerCase().compareTo(right.getName().toLowerCase());
        }
    }
}
