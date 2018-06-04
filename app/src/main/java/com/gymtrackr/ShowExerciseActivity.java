package com.gymtrackr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Domain.Exercise;

public class ShowExerciseActivity extends AppCompatActivity {

    public static String EXTRA_EXERCISE = "Exercise";

    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise);

        Bundle bundle = getIntent().getExtras();
        int position = 0;
        if(bundle != null) {
            position = bundle.getInt(EXTRA_EXERCISE);
            exercise = DomainController.getInstance().getExerciseList().get(position);
        }
        else exercise = new Exercise();

        TextView name = findViewById(R.id.textViewExerciseName);
        name.setText(exercise.getName());
        TextView seriesReps = findViewById(R.id.textViewExerciseSeriesReps);
        seriesReps.setText(String.valueOf(exercise.getSeries())+"x"+String.valueOf(exercise.getRepetitions()));
        TextView muscles = findViewById(R.id.textViewExerciseMuscles);
        String allMuscles = "";
        for (String muscle: exercise.getMuscles() ) {
            allMuscles += (muscle + ", ");
        }
        muscles.setText(allMuscles);

    }
}
