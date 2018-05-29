package com.gymtrackr.Domain;

import com.gymtrackr.GymTrackr;
import com.gymtrackr.Persistence.PersistenceManager;
import com.gymtrackr.Persistence.PersistenceManagerImpl;
import com.gymtrackr.Throwables.InsertErrorThrowable;

public class DomainController {
    private static DomainController myDomainController = null;
    private ExerciseList exerciseList;
    private PersistenceManager persistenceManager;

    private DomainController() {
        persistenceManager = new PersistenceManagerImpl(GymTrackr.getContext());
        exerciseList = new ExerciseList(persistenceManager.getExercises());
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
        try {
            persistenceManager.putExercise(exercise);
        } catch (InsertErrorThrowable insertErrorThrowable) {
            insertErrorThrowable.printStackTrace();
        }
        exerciseList.addExercise(exercise);

    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

}
