package com.gymtrackr.Persistence;


import android.content.Context;

import com.gymtrackr.Domain.Exercise;
import com.gymtrackr.Domain.Routine;
import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.util.ArrayList;
import java.util.List;

public class PersistenceManagerImpl implements PersistenceManager {

    MySQLiteOpenHelper mySQLiteOpenHelper;

    public PersistenceManagerImpl(Context context) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

    @Override
    public void putRoutine(Routine routine) throws InsertErrorThrowable {
        mySQLiteOpenHelper.putRoutine(routine.getName(),Integer.toString(routine.getDayOfTheWeek()));
    }

    @Override
    public List<Routine> getRoutines() {
        List<List<String>> rawRoutinesList = mySQLiteOpenHelper.getTable(MySQLiteOpenHelper.ROUTINE_TABLE_NAME);
        List<Routine> routinesList = new ArrayList<>();

        for(List<String> rawRoutine:rawRoutinesList) {
            Routine routine = new Routine();
            routine.setName(rawRoutine.get(0));
            routine.setDayOfTheWeek(Integer.valueOf(rawRoutine.get(1)));
            routinesList.add(routine);
        }

        return routinesList;
    }

    @Override
    public void updateRoutineName(String oldName, String newName) {
        mySQLiteOpenHelper.updateRoutineName(oldName,newName);
    }

    @Override
    public void updateRoutineDayOfTheWeek(String routineName, int newDay) {
        mySQLiteOpenHelper.updateRoutineDayOfTheWeek(routineName,String.valueOf(newDay));
    }

    @Override
    public void putExercise(Exercise exercise) throws InsertErrorThrowable {
        mySQLiteOpenHelper.putExercise(exercise.getName(),String.valueOf(exercise.getReps()),
                String.valueOf(exercise.getSeries()));
    }

    @Override
    public List<Exercise> getExercises() {
        List<List<String>> rawExercisesList = mySQLiteOpenHelper.getTable(MySQLiteOpenHelper.EXERCISE_TABLE_NAME);
        List<Exercise> exercisesList = new ArrayList<>();

        for(List<String> rawExercise:rawExercisesList) {
            Exercise exercise = new Exercise();
            exercise.setName(rawExercise.get(0));
            System.out.println(rawExercise.get(1));
            exercise.setReps(Integer.parseInt(rawExercise.get(1)));
            exercise.setSeries(Integer.parseInt(rawExercise.get(2)));
            exercisesList.add(exercise);
        }

        return exercisesList;
    }

    @Override
    public void putJRE(Routine routine) throws InsertErrorThrowable {
        List<Exercise> exerciseList = routine.getExercises();

        for(Exercise exercise: exerciseList) {
            mySQLiteOpenHelper.putJRE(routine.getName(),exercise.getName());
        }
    }

    @Override
    public void putJRE(String routineName, String exerciseName) throws InsertErrorThrowable {
        mySQLiteOpenHelper.putJRE(routineName, exerciseName);
    }

    @Override
    public List<String> getJRE(String routineName) {
        return mySQLiteOpenHelper.getJRE(routineName);
    }

    @Override
    public void deleteJRE(String routineName) {
        mySQLiteOpenHelper.deleteJRE(routineName);
    }
}
