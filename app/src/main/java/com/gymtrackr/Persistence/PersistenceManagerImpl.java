package com.gymtrackr.Persistence;


import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.gymtrackr.Domain.Exercise;
import com.gymtrackr.Domain.ExerciseDone;
import com.gymtrackr.Domain.Routine;
import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public void updateExerciseName(String oldName, String newName) {
        mySQLiteOpenHelper.updateExerciseName(oldName, newName);
    }

    @Override
    public void updateExerciseSeries(String name, String newSeries) {
        mySQLiteOpenHelper.updateExerciseSeries(name,newSeries);
    }

    @Override
    public void updateExerciseRepetitions(String name, String newReps) {
        mySQLiteOpenHelper.updateExerciseRepetitions(name,newReps);
    }

    @Override
    public void putExercise(Exercise exercise) throws InsertErrorThrowable {
        String muscles = new String();
        for (String muscle: exercise.getMuscles()) {
            muscles = muscles.concat(muscle + ":");
        }
        mySQLiteOpenHelper.putExercise(exercise.getName(), exercise.getRepetitions(), exercise.getSeries(), muscles);
    }

    @Override
    public List<Exercise> getExercises() {
        List<List<String>> rawExercisesList = mySQLiteOpenHelper.getTable(MySQLiteOpenHelper.EXERCISE_TABLE_NAME);
        List<Exercise> exercisesList = new ArrayList<>();

        for(List<String> rawExercise:rawExercisesList) {
            Exercise exercise = new Exercise();
            exercise.setName(rawExercise.get(0));
            exercise.setRepetitions(Integer.parseInt(rawExercise.get(1)));
            exercise.setSeries(Integer.parseInt(rawExercise.get(2)));
            ArrayList<String> muscles = new ArrayList<String>();
            Collections.addAll(muscles, rawExercise.get(3).split(":"));
            exercise.setMuscles(muscles);
            exercisesList.add(exercise);
        }

        return exercisesList;
    }

    @Override
    public void putExerciseDone(String name,String repetitions, String series, String weight)
            throws InsertErrorThrowable {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        Date date = new Date();
        System.out.println("DATE: " + sdf.format(date));
        mySQLiteOpenHelper.putDoneExercise(name,sdf.format(date),repetitions,series,weight);
    }

    @Override
    public List<String> getLastExerciseDone(String exerciseName) {
        return mySQLiteOpenHelper.getLastExerciseDone(exerciseName);
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

    @Override
    public void deleteExercise(String exerciseName) {
        mySQLiteOpenHelper.deleteExercise(exerciseName);
    }

    @Override
    public void deleteRoutine(String routineName) {
        mySQLiteOpenHelper.deleteRoutine(routineName);
    }

    @Override
    public List<Pair<String, Integer>> getExerciseHistory(String name) {
        List<List<String>> rawExercisesList = mySQLiteOpenHelper.getExerciseHistory(name);
        List<Pair<String, Integer>> pairList = new ArrayList<>();

        for(List<String> rawExercise:rawExercisesList) {
            String date = rawExercise.get(0);
            Integer weight = Integer.parseInt(rawExercise.get(1));
            Pair<String, Integer> pair = new Pair<String, Integer>(date, weight);
            pairList.add(pair);
        }

        return pairList;
    }
}
