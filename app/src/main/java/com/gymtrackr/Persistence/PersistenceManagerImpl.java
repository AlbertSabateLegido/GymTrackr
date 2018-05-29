package com.gymtrackr.Persistence;


import android.content.Context;

import com.gymtrackr.Domain.DayOfTheWeek;
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
        mySQLiteOpenHelper.putRoutine(routine.getName(),DayOfTheWeek.toString(routine.getDayOfTheWeek()));
    }

    @Override
    public List<Routine> getRoutines() {
        List<List<String>> rawRoutinesList = mySQLiteOpenHelper.getRoutines();
        List<Routine> routinesList = new ArrayList<>();

        for(List<String> rawRoutine:rawRoutinesList) {
            Routine routine = new Routine();
            routine.setName(rawRoutine.get(0));
            routine.setDayOfTheWeek(DayOfTheWeek.toDayOfTheWeek(rawRoutine.get(1)));
            routinesList.add(routine);
        }

        return routinesList;
    }

    @Override
    public void putExercise(Exercise exercise) throws InsertErrorThrowable {
        mySQLiteOpenHelper.putExercise(exercise.getName(),exercise.getReps(),exercise.getSeries());
    }

    @Override
    public void putJRE(Routine routine) throws InsertErrorThrowable {
        List<Exercise> exerciseList = routine.getExercises();

        for(Exercise exercise: exerciseList) {
            mySQLiteOpenHelper.putJRE(routine.getName(),exercise.getName());
        }
    }

    @Override
    public void putJRE(String routineName, String exerciceName) throws InsertErrorThrowable {
        mySQLiteOpenHelper.putJRE(routineName,exerciceName);
    }
}
