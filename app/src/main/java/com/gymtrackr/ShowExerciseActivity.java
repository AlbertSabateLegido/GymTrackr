package com.gymtrackr;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Domain.Exercise;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShowExerciseActivity extends AppCompatActivity {

    public static String EXTRA_EXERCISE = "Exercise";

    private Exercise exercise;

    private boolean isEditing = false;
    EditText exerciseNameField;
    EditText exerciseSeriesField;
    EditText exerciseRepetitionsField;
    ImageView editButton;
    ImageView deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise);

        // getting the Exercise to be shown name
        Bundle bundle = getIntent().getExtras();
        final String exerciseName;
        if(bundle != null) {
            exerciseName = bundle.getString(EXTRA_EXERCISE);
            // getting the Exercise given its name
            exercise = DomainController.getInstance().getExercise(exerciseName);
        }
        else exercise = new Exercise();

        // getting all the fields
        exerciseNameField = findViewById(R.id.editTextExerciseName);
        exerciseSeriesField = findViewById(R.id.editTextExerciseSeries);
        exerciseRepetitionsField = findViewById(R.id.editTextExerciseRepetitions);

        getSupportActionBar().hide();

        // setting up the fields
        exerciseNameField.setText(exercise.getName());
        exerciseNameField.setEnabled(false);
        exerciseSeriesField.setText(String.valueOf(exercise.getSeries()));
        exerciseSeriesField.setEnabled(false);
        exerciseRepetitionsField.setText(String.valueOf(exercise.getRepetitions()));
        exerciseRepetitionsField.setEnabled(false);

        //-----------NOT THE FINAL VERSION-------------
        TextView muscles = findViewById(R.id.textViewExerciseMuscles);
        String allMuscles = "";
        boolean first = true;
        for (String muscle: exercise.getMuscles() ) {
            if (first) {
                allMuscles += (muscle);
                first = false;
            }
            else allMuscles += (", " + muscle);
        }
        muscles.setText(allMuscles);
        //----------------------------------------------

        // setting up the chart
        List<Pair<String, Integer>> exerciseInfo = DomainController.getInstance().getExerciseInformationDetailed(exercise.getName());
        int nb_exercises = 5;
        DataPoint[] exerciseData = new DataPoint[nb_exercises];
        String[] labelsX = new String[nb_exercises];
        for (int i = 0; i < nb_exercises; ++i) {
            exerciseData[i] = new DataPoint(i,0);
            labelsX[i] = "No data";
        }
        int max_weight = 0;
        for (int i= 0; i < Math.min(exerciseInfo.size(),nb_exercises); ++i) {
            int weight = exerciseInfo.get(exerciseInfo.size() - Math.min(exerciseInfo.size(),nb_exercises) + i).second;
            exerciseData[i] = new DataPoint(i, weight);
            labelsX[i] = exerciseInfo.get(i).first.substring(6,8) + "/" + exerciseInfo.get(i).first.substring(4,6);
            if (weight > max_weight) max_weight = weight;
        }
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(exerciseData);
        GraphView graph = findViewById(R.id.graph);
        graph.addSeries(series);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(max_weight+10);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(labelsX);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(90+45-10);
        graph.getGridLabelRenderer().setLabelsSpace(10);
        graph.setTitle(getString(R.string.graph_title));
        graph.setTitleColor(getResources().getColor(R.color.dark_grey));
        graph.getGridLabelRenderer().setPadding(40);
        graph.getGridLabelRenderer().setVerticalAxisTitle(getString(R.string.graph_axisY));
        graph.getGridLabelRenderer().setVerticalLabelsColor(getResources().getColor(R.color.dark_grey));
        graph.getGridLabelRenderer().setHorizontalLabelsColor(getResources().getColor(R.color.dark_grey));
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(getResources().getColor(R.color.dark_grey));
        series.setSpacing(50);
        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(getResources().getColor(R.color.dark_grey));
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return getResources().getColor(R.color.colorAccent);
            }
        });
        //series.setValuesOnTopSize(50);


        // setting up the behaviour of the edit button
        editButton = findViewById(R.id.imageViewEditName);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEditing = !isEditing;

                exerciseNameField.setEnabled(isEditing);
                exerciseSeriesField.setEnabled(isEditing);
                exerciseRepetitionsField.setEnabled(isEditing);
                if (isEditing) {
                    editButton.setImageResource(R.drawable.ic_action_done);
                    exerciseNameField.requestFocus();
                    int pos = exerciseNameField.getText().length();
                    exerciseNameField.setSelection(pos);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(exerciseNameField, InputMethodManager.SHOW_IMPLICIT);
                }
                else {
                    editButton.setImageResource(R.drawable.ic_action_edit);
                    DomainController.getInstance().updateExercise(exercise.getName(),
                            exerciseNameField.getText().toString(),
                            exerciseSeriesField.getText().toString(),
                            exerciseRepetitionsField.getText().toString());
                }
            }
        });

        deleteButton = findViewById(R.id.imageViewDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DomainController.getInstance().deleteExercise(exerciseNameField.getText().toString());
                finish();
            }
        });
    }
}
