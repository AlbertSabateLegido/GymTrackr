package com.gymtrackr.Persistence;

import com.gymtrackr.Domain.Exercise;
import com.gymtrackr.Domain.Routine;
import com.gymtrackr.Throwables.InsertErrorThrowable;

public interface PersistenceManager {

    void putRoutine(Routine routine) throws InsertErrorThrowable;

    void putExercise(Exercise exercise) throws InsertErrorThrowable;

    void putJRE(Routine routine) throws InsertErrorThrowable;

    void putJRE(String routineName,String exerciceName) throws InsertErrorThrowable;
}
