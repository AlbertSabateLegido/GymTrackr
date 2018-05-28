package com.gymtrackr;

import java.util.ArrayList;

public class Exercise {

    private String name;
    private ArrayList<String> musclesInvolved;
    private int reps;
    private int series;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMusclesInvolved() {
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
}
