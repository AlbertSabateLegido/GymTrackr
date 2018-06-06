package com.gymtrackr.Domain;

import android.util.Pair;

import com.gymtrackr.GymTrackr;
import com.gymtrackr.Persistence.PersistenceManager;
import com.gymtrackr.Persistence.PersistenceManagerImpl;
import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DomainController {
    private static DomainController myDomainController = null;
    private List<Routine> routinesList;
    private List<Exercise> exerciseList;
    private PersistenceManager persistenceManager;
    private Integer currentRoutine;

    private DomainController() {
        persistenceManager = new PersistenceManagerImpl(GymTrackr.getContext());
        routinesList = persistenceManager.getRoutines();
        exerciseList = persistenceManager.getExercises();
        currentRoutine = null;
    }

    public static synchronized DomainController getInstance(){
        if(null == myDomainController){
            myDomainController = new DomainController();
        }
        return myDomainController;
    }

    public void addExercise(String name, int series, int repetitions, ArrayList<String> muscles) {
        Exercise exercise = new Exercise(name, series, repetitions);
        exercise.setMuscles(muscles);
        try {
            persistenceManager.putExercise(exercise);
        } catch (InsertErrorThrowable insertErrorThrowable) {
            insertErrorThrowable.printStackTrace();
        }
        exerciseList.add(exercise);
        Collections.sort(exerciseList, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise o1, Exercise o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public void updateExercise(String oldName, String newName, String series, String reps) {
        int i = 0;
        boolean found = false;
        while (i < exerciseList.size() && !found) {
            found = exerciseList.get(i).getName().equals(oldName);
            if (found) {
                if (!newName.equals(oldName)) {
                    exerciseList.get(i).setName(newName);
                    persistenceManager.updateExerciseName(oldName, newName);
                    oldName = newName;
                }
                if (Integer.parseInt(series) != exerciseList.get(i).getSeries()) {
                    exerciseList.get(i).setSeries(Integer.parseInt(series));
                    persistenceManager.updateExerciseSeries(oldName, series);
                }
                if (Integer.parseInt(reps) != exerciseList.get(i).getRepetitions()) {
                    exerciseList.get(i).setRepetitions(Integer.parseInt(reps));
                    persistenceManager.updateExerciseSeries(oldName, reps);
                }

            }
            ++i;
        }
        Collections.sort(exerciseList, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise o1, Exercise o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public Exercise getExercise(String name) {
        for (Exercise exercise: exerciseList) {
            if (exercise.getName().equals(name)) return exercise;
        }
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
        //Collections.sort(exerciseNames);
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
        int i = -1;
        String auxName;
        do {
             ++i;
             auxName = routinesList.get(i).getName();
        } while(i+1 < routinesList.size() && (!name.equals(auxName)));

        List<String> rawRoutine = new ArrayList<>();
        if(i < routinesList.size()) {
            Routine routine = routinesList.get(i);
            rawRoutine.add(Integer.toString(routine.getDayOfTheWeek()));
        }
        return rawRoutine;
    }

    public void addRoutine(String routineName, int dayOfTheWeek) {
        Routine routine = new Routine(routineName,dayOfTheWeek);
        routinesList.add(routine);
        try {
            persistenceManager.putRoutine(routine);
        } catch (InsertErrorThrowable insertErrorThrowable) {
            insertErrorThrowable.printStackTrace();
        }
    }

    public void assignExercises(String routineName, List<String> assignedExerciseNamesList) {
        for(String exerciseName:assignedExerciseNamesList) {
            try {
                persistenceManager.putJRE(routineName,exerciseName);
            } catch (InsertErrorThrowable insertErrorThrowable) {
                insertErrorThrowable.printStackTrace();
            }
        }
    }

    public List<String> getAssignedExercises(String routineName) {
        return persistenceManager.getJRE(routineName);
    }

    public void setRoutineDayOfTheWeek(String routineName, int newDay) {
        int i = -1;
        String auxName;
        do {
            ++i;
            auxName = routinesList.get(i).getName();
        } while(i+1 < routinesList.size() && (!routineName.equals(auxName)));
        routinesList.get(i).setDayOfTheWeek(newDay);
        persistenceManager.updateRoutineDayOfTheWeek(routineName,newDay);
    }

    public void setRoutineName(String oldName, String newName) {
        int i = -1;
        String auxName;
        do {
            ++i;
            auxName = routinesList.get(i).getName();
        } while(i+1 < routinesList.size() && (!oldName.equals(auxName)));
        routinesList.get(i).setName(newName);
        persistenceManager.updateRoutineName(oldName,newName);
    }

    public void deleteAssignedExercises(String routineName) {
        persistenceManager.deleteJRE(routineName);
    }

    public void deleteExercise(String exerciseName) {
        persistenceManager.deleteExercise(exerciseName);
        boolean found = false;
        int i = 0;
        while(i < exerciseList.size() && !found) {
            found = exerciseList.get(i).getName().equals(exerciseName);
            if (found) exerciseList.remove(i);
            ++i;
        }
    }

    public void deleteRoutine(String routineName) {
        persistenceManager.deleteRoutine(routineName);
        boolean found = false;
        int i = 0;
        while(i < routinesList.size() && !found) {
            found = routinesList.get(i).getName().equals(routineName);
            if (found) routinesList.remove(i);
            ++i;
        }
    }

    public List<String> startRoutine(String routineName) {
        int i = -1;
        String auxName;
        do {
            ++i;
            auxName = routinesList.get(i).getName();
        } while(i+1 < routinesList.size() && (!routineName.equals(auxName)));
        currentRoutine = i;
        List<Exercise> exercises = new ArrayList<>();
        List<String> exercisesNames = getAssignedExercises(routineName);
        for(String exerciseName:exercisesNames) {
            exercises.add(new Exercise(exerciseName));
        }
        routinesList.get(currentRoutine).setExercises(exercises);
        System.out.println("ROUTINE STARTED");
        return exercisesNames;
    }

    public List<String> getExerciseInformation(String exerciseName) {
        return persistenceManager.getLastExerciseDone(exerciseName);
    }

    public List<Pair<String, Integer>> getExerciseInformationDetailed(String exerciseName) {
        return persistenceManager.getExerciseHistory(exerciseName);
    }

    public String getCurrentRoutineName() {
        return exerciseList.get(currentRoutine).getName();
    }

    public void finishRoutine() {
        currentRoutine = null;
        System.out.println("ROUTINE FINISHED");
    }

    public  void realizeExercise(String exerciseName, String repetitions, String series, String weight) {
        if(currentRoutine == null) return;

        try {
            persistenceManager.putExerciseDone(exerciseName,repetitions,series,weight);
        } catch (InsertErrorThrowable insertErrorThrowable) {
            insertErrorThrowable.printStackTrace();
        }
        List<Exercise> exercises = routinesList.get(currentRoutine).getExercises();
        int i = -1;
        String auxName;
        do {
            ++i;
            auxName = exercises.get(i).getName();
        } while(i+1 < exercises.size() && (!exerciseName.equals(auxName)));
        exercises.remove(i);
    }

    public List<String> getRemainExercises() {
        if(currentRoutine == null) return null;
        List<String> remainExercises = new ArrayList<>();

        for(Exercise exercise:routinesList.get(currentRoutine).getExercises()) {
            remainExercises.add(exercise.getName());
        }

        return remainExercises;
    }
}
