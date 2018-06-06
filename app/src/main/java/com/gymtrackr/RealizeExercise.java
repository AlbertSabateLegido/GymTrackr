package com.gymtrackr;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gymtrackr.Domain.DomainController;

import java.util.List;

public class RealizeExercise extends AppCompatActivity {

    public static String EXTRA_EXERCISE_NAME = "exercise_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_dialog);

        String exerciseName = new String();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) exerciseName = bundle.getString(EXTRA_EXERCISE_NAME);

        final EditText etWeight = findViewById(R.id.etWeight);

        Button bCancel = findViewById(R.id.bCancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<String> exerciseInfo = DomainController.getInstance().getExerciseInformation(exerciseName);
        String weight = "";
        if (exerciseInfo.size() > 2) weight = String.valueOf(exerciseInfo.get(2));
        etWeight.setText(weight);

        Button bAccept = findViewById(R.id.bAccept);
        final String finalExerciseName = exerciseName;
        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String repetitions = String.valueOf(DomainController.getInstance().getExercise(finalExerciseName).getRepetitions());//etRepetitions.getText().toString();
                if(repetitions.isEmpty()) return;
                String series = String.valueOf(DomainController.getInstance().getExercise(finalExerciseName).getSeries());//etSeries.getText().toString();
                if(series.isEmpty()) return;
                String weight = etWeight.getText().toString();
                if(weight.isEmpty()) return;
                DomainController.getInstance().realizeExercise(finalExerciseName,repetitions,series,weight);
                finish();
            }
        });
    }
}