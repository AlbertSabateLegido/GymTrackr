package com.gymtrackr;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.util.ArrayList;

public class AddExerciseActivity extends AppCompatActivity {

    private EditText exerciseName;
    private EditText exerciseSeries;
    private EditText exerciseReps;
    private ArrayList<CheckBox> muscleGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        getSupportActionBar().setTitle(getString(R.string.activity_new_exercise));

        exerciseName = findViewById(R.id.editTextExerciseName);
        exerciseSeries = findViewById(R.id.editTextExerciseSeries);
        exerciseReps = findViewById(R.id.editTextExerciseRepetitions);
        muscleGroups = new ArrayList<>();
        muscleGroups.add((CheckBox)findViewById(R.id.checkBoxAbs));
        muscleGroups.add((CheckBox)findViewById(R.id.checkBoxBiceps));
        muscleGroups.add((CheckBox)findViewById(R.id.checkBoxTriceps));
        muscleGroups.add((CheckBox)findViewById(R.id.checkBoxBack));
        muscleGroups.add((CheckBox)findViewById(R.id.checkBoxChest));
        muscleGroups.add((CheckBox)findViewById(R.id.checkBoxLegs));
        muscleGroups.add((CheckBox)findViewById(R.id.checkBoxShoulder));

        FloatingActionButton fab = findViewById(R.id.fabAddExercise);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DomainController domainController = DomainController.getInstance();
                String name;
                int series, reps;
                ArrayList<String> muscles = new ArrayList<>();
                if (!exerciseName.getText().toString().matches(""))
                    name = exerciseName.getText().toString();
                else name = "New exercise";
                if (!exerciseSeries.getText().toString().matches(""))
                    series = Integer.parseInt(exerciseSeries.getText().toString());
                else series = 0;
                if (!exerciseReps.getText().toString().matches(""))
                    reps = Integer.parseInt(exerciseReps.getText().toString());
                else reps = 0;
                for (CheckBox muscle: muscleGroups) {
                    if (muscle.isChecked()) muscles.add(muscle.getText().toString());
                }
                try {
                    domainController.addExercise(name, series, reps, muscles);
                    finish();
                } catch (InsertErrorThrowable insertErrorThrowable) {
                    insertErrorThrowable.printStackTrace();
                    Toast.makeText(GymTrackr.getContext(),GymTrackr.getContext().getResources().
                            getString(R.string.repeteated_exercise_name), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
