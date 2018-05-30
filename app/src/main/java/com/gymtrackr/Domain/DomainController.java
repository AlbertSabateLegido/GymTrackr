package com.gymtrackr.Domain;

import com.gymtrackr.GymTrackr;
import com.gymtrackr.Persistence.PersistenceManager;
import com.gymtrackr.Persistence.PersistenceManagerImpl;
import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DomainController {
    private static DomainController myDomainController = null;
    private List<Routine> routinesList;
    private List<Exercise> exerciseList;
    private PersistenceManager persistenceManager;

    private DomainController() {
        persistenceManager = new PersistenceManagerImpl(GymTrackr.getContext());
        routinesList = persistenceManager.getRoutines();
        exerciseList = persistenceManager.getExercises();
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
        exerciseList.add(exercise);
    }

    public Exercise getExercise(String name) {
        return null;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public List<String> getExerciseNames() {
        List<String> exerciseNames = new ArrayList<>();
        for (Exercise exercise:exerciseList) {
            exerciseNames.add(exercise.getName());
        }
        Collections.sort(exerciseNames);
        return exerciseNames;
    }

    public String getLastExerciseName() {
        return exerciseList.get(exerciseList.size()-1).getName();
    }

    public int getExerciseListSize() {
        return exerciseList.size();
    }

    public List<String> getRoutinesNames() {
        List<String> routinesNames = new ArrayList<>();
        for (Routine routine:routinesList) {
            routinesNames.add(routine.getName());
        }
        Collections.sort(routinesNames);
        return routinesNames;
    }

    public List<String> getRoutineInformation(String name) {
        int i = 0;
        String auxName;
        do {
             auxName = routinesList.get(i).getName();
             ++i;
        } while(i < routinesList.size() && (name != auxName));

        List<String> rawRoutine = new ArrayList<>();
        if(i < routinesList.size()) {
            Routine routine = routinesList.get(i);
            rawRoutine.add(DayOfTheWeek.toString(routine.getDayOfTheWeek()));
        }
        return rawRoutine;
    }
}