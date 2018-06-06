package com.gymtrackr;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Pair;
import android.widget.TextView;

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
        getSupportActionBar().setTitle(exercise.getName());
        TextView seriesReps = findViewById(R.id.textViewExerciseSeriesReps);
        seriesReps.setText(String.valueOf(exercise.getSeries())+"x"+String.valueOf(exercise.getRepetitions()));
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
        //graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(90+45-10);
        graph.getGridLabelRenderer().setLabelsSpace(10);
        graph.setTitle(getString(R.string.graph_title));
        graph.getGridLabelRenderer().setPadding(40);
        graph.getGridLabelRenderer().setVerticalAxisTitle(getString(R.string.graph_axisY));


        series.setSpacing(50);


        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(getResources().getColor(R.color.colorAccent));
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return getResources().getColor(R.color.colorAccent);
            }
        });
        //series.setValuesOnTopSize(50);

    }
}
