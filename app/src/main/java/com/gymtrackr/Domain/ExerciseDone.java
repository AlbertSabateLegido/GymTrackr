package com.gymtrackr.Domain;

public class ExerciseDone extends Exercise {

    private String date;
    private int weight;

    public ExerciseDone() {
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
