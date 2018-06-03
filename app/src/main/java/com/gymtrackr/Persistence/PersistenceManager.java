package com.gymtrackr.Persistence;

import com.gymtrackr.Domain.Exercise;
import com.gymtrackr.Domain.Routine;
import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.util.List;

public interface PersistenceManager {

    void putRoutine(Routine routine) throws InsertErrorThrowable;

    List<Routine> getRoutines();

    void updateRoutineName(String oldName, String newName);

    void updateRoutineDayOfTheWeek(String finalRoutineName, int i);

    void putExercise(Exercise exercise) throws InsertErrorThrowable;

    List<Exercise> getExercises();

    void putJRE(Routine routine) throws InsertErrorThrowable;

    void putJRE(String routineName,String exerciseName) throws InsertErrorThrowable;

    List<String> getJRE(String routineName);

    void deleteJRE(String routineName);
}
