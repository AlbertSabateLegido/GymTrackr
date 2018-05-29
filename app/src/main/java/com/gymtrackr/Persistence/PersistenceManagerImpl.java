package com.gymtrackr.Persistence;


import android.content.Context;

import com.gymtrackr.Domain.Exercise;
import com.gymtrackr.Domain.Routine;
import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.util.List;

public class PersistenceManagerImpl implements PersistenceManager {

    MySQLiteOpenHelper mySQLiteOpenHelper;

    public PersistenceManagerImpl(Context context) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

    @Override
    public void putRoutine(Routine routine) throws InsertErrorThrowable {
        mySQLiteOpenHelper.putRoutine(routine.getName(),routine.getDayOfTheWeek().toString());
    }

    @Override
    public void putExercice(Exercise exercise) throws InsertErrorThrowable {
        mySQLiteOpenHelper.putExercice(exercise.getName(),exercise.getReps(),exercise.getSeries());
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
