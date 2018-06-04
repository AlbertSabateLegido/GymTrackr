package com.gymtrackr.Domain;

import java.util.ArrayList;

public class Exercise {

    private String name;
    private int series;
    private int repetitions;

    private ArrayList<String> muscles;

    public Exercise() {}

    public Exercise (String name) {
        this.name = name;
    }

    public Exercise (String name, int series, int repetitions) {
        this.name = name;
        this.series = series;
        this.repetitions = repetitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public ArrayList<String> getMuscles() {
        return muscles;
    }

    public void setMuscles(ArrayList<String> muscles) {
        this.muscles = muscles;
    }

}
