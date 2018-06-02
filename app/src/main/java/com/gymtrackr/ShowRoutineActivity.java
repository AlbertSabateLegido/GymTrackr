package com.gymtrackr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;

import java.util.List;

public class ShowRoutineActivity extends AppCompatActivity {

    public static String EXTRA_ROUTINE_NAME = "RoutineName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_routine);

        String routineName = new String();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) routineName = bundle.getString(EXTRA_ROUTINE_NAME);

        System.out.println(routineName);

        List<String> rawRoutine = DomainController.getInstance().getRoutineInformation(routineName);
        List<String> exercises  = DomainController.getInstance().getAssignedExercises(routineName);

        TextView tvName = findViewById(R.id.etRoutineName);
        tvName.setText(routineName);

        Spinner spinner = findViewById(R.id.spinnerDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_the_week_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(Integer.valueOf(rawRoutine.get(0)));

        ExercisesAdapter exercisesAdapter = new ShowAssignedExercisesAdapter(exercises);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
    }
}
