package com.gymtrackr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gymtrackr.Domain.DomainController;

import java.util.ArrayList;
import java.util.List;

public class AddRoutineActivity extends AppCompatActivity {

    public static String ASSIGNED_EXERCISES = "assigned_exercises";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        List<String> assignedExerciseNamesList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            assignedExerciseNamesList = bundle.getStringArrayList(ASSIGNED_EXERCISES);
        }

        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(assignedExerciseNamesList,ExercisesAdapter.SHOW);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));

        final Spinner spinner = findViewById(R.id.spinnerDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_the_week_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText etName = findViewById(R.id.etRoutineName);

        FloatingActionButton fab = findViewById(R.id.fab);
        final List<String> finalAssignedExerciseNamesList = assignedExerciseNamesList;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String routineName = etName.getText().toString();
                if(routineName.isEmpty()) {
                    Toast.makeText(GymTrackr.getContext(),GymTrackr.getContext().getResources().
                                    getString(R.string.empty_name), Toast.LENGTH_SHORT).show();
                    return;
                }

                int dayOfTheWeek = spinner.getSelectedItemPosition();
                DomainController.getInstance().addRoutine(routineName,dayOfTheWeek);
                DomainController.getInstance().assignExercises(routineName, finalAssignedExerciseNamesList);

                Intent intent = new Intent(GymTrackr.getContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}
