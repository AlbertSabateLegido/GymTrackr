package com.gymtrackr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gymtrackr.Domain.DomainController;

import java.util.List;

public class StartRoutineActivity extends AppCompatActivity {

    public static String EXTRA_ROUTINE_NAME = "routineName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assigned_exercises);

        String routineName = new String();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) routineName = bundle.getString(EXTRA_ROUTINE_NAME);

        List<String> exerciseList = DomainController.getInstance().getAssignedExercises(routineName);

        StartRoutineAdapter startRoutineAdapter = new StartRoutineAdapter(exerciseList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(startRoutineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
