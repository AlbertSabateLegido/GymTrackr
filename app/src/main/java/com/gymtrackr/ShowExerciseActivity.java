package com.gymtrackr;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Domain.Exercise;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

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

        List<Pair<String, Integer>> exerciseInfo = DomainController.getInstance().getExerciseInformationDetailed(exercise.getName());

        GraphView graph = (GraphView) findViewById(R.id.graph);
        DataPoint[] exerciseData = new DataPoint[exerciseInfo.size()+1];
        exerciseData[0] = new DataPoint(0, 0);
        for (int i= 1; i < exerciseData.length; ++i) {
            exerciseData[i] = new DataPoint(i-1, exerciseInfo.get(i-1).second);
        }
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(exerciseData);

        graph.addSeries(series);

        graph.getGridLabelRenderer().setNumHorizontalLabels(5);

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);


        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        //series.setValuesOnTopSize(50);

    }
}
