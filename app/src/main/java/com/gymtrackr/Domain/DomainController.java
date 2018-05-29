package com.gymtrackr.Domain;

public class DomainController {
    private static DomainController myDomainController = null;
    private ExerciseList exerciseList;

    private DomainController() {
        exerciseList = new ExerciseList();
        loadSampleExercises();
    }

    public static synchronized DomainController getInstance(){
        if(null == myDomainController){
            myDomainController = new DomainController();
        }
        return myDomainController;
    }

    public void addExercise(String name, int series, int reps) {
        Exercise exercise = new Exercise(name);
        exercise.setSeries(series);
        exercise.setReps(reps);
        exerciseList.addExercise(exercise);
    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    private void loadSampleExercises() {
        Exercise exercise1 = new Exercise("Bench press");
        exercise1.setSeries(4);
        exercise1.setReps(10);
        exerciseList.addExercise(exercise1);
        Exercise exercise2 = new Exercise("Biceps curl");
        exercise2.setSeries(4);
        exercise2.setReps(10);
        exerciseList.addExercise(exercise2);
        Exercise exercise3 = new Exercise("Triceps extension");
        exercise3.setSeries(4);
        exercise3.setReps(15);
        exerciseList.addExercise(exercise3);
    }
}
