package com.gymtrackr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddRoutineActivity extends AppCompatActivity {

    public static String ASSIGNED_EXERCISES = "assigned_exercises";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_routine);

        List<String> assignedExerciseNamesList = new ArrayList<>();

        if(bundle != null) assignedExerciseNamesList = bundle.getStringArrayList(ASSIGNED_EXERCISES);

        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(assignedExerciseNamesList,ExercisesAdapter.SHOW);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
    }

}
