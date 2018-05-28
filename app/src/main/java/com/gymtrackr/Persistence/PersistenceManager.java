package com.gymtrackr.Persistence;

import com.gymtrackr.Exercise;
import com.gymtrackr.Routine;
import com.gymtrackr.Throwables.InsertErrorThrowable;

public interface PersistenceManager {

    void putRoutine(Routine routine) throws InsertErrorThrowable;

    void putExercice(Exercise exercise) throws InsertErrorThrowable;

    void putJRE(Routine routine) throws InsertErrorThrowable;

    void putJRE(String routineName,String exerciceName) throws InsertErrorThrowable;
}
