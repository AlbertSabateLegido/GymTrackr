package com.gymtrackr;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gymtrackr.Domain.DomainController;

public class AddExerciseActivity extends AppCompatActivity {

    private EditText exerciseName;
    private EditText exerciseSeries;
    private EditText exerciseReps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        exerciseName = findViewById(R.id.editText);
        exerciseSeries = findViewById(R.id.editText2);
        exerciseReps = findViewById(R.id.editText3);

        FloatingActionButton fab = findViewById(R.id.fabAddExercise);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DomainController domainController = DomainController.getInstance();
                String name;
                int series, reps;
                if (!exerciseName.getText().toString().matches(""))
                    name = exerciseName.getText().toString();
                else name = "New exercise";
                if (!exerciseSeries.getText().toString().matches(""))
                    series = Integer.parseInt(exerciseSeries.getText().toString());
                else series = 0;
                if (!exerciseReps.getText().toString().matches(""))
                    reps = Integer.parseInt(exerciseReps.getText().toString());
                else reps = 0;
                domainController.addExercise(name, series, reps);
                finish();
            }
        });
    }
}
