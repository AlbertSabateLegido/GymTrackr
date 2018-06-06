package com.gymtrackr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;

import java.util.List;

public class ShowRoutineActivity extends AppCompatActivity {

    public static String EXTRA_ROUTINE_NAME = "RoutineName";

    private String routineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_routine);

        getSupportActionBar().hide();


        Bundle bundle = getIntent().getExtras();
        if(bundle != null) routineName = bundle.getString(EXTRA_ROUTINE_NAME);
        else routineName = new String();

        System.out.println(routineName);

        List<String> rawRoutine = DomainController.getInstance().getRoutineInformation(routineName);

        TextView tvName = findViewById(R.id.etRoutineName);
        tvName.setText(routineName);

        Spinner spinner = findViewById(R.id.spinnerDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_the_week_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(Integer.valueOf(rawRoutine.get(0)));
        spinner.setEnabled(false);
        final String finalRoutineName = routineName;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DomainController.getInstance().setRoutineDayOfTheWeek(finalRoutineName,i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loadRecyclerView(routineName);

        ImageView ivEdit = findViewById(R.id.ivEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GymTrackr.getContext(),EditNameActivity.class);
                intent.putExtra(EditNameActivity.EXTRA_NAME, finalRoutineName);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        CardView cardViewStartRoutine = findViewById(R.id.cardViewStartRoutine);
        cardViewStartRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GymTrackr.getContext(),RealizeRoutineActivity.class);
                intent.putExtra(RealizeRoutineActivity.EXTRA_ROUTINE_NAME,routineName);
                GymTrackr.getContext().startActivity(intent);
            }
        });
        CardView cardViewAssignExercise = findViewById(R.id.cardViewAssignExercise);
        cardViewAssignExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GymTrackr.getContext(),EditAssignedExercisesActivity.class);
                intent.putExtra(EditAssignedExercisesActivity.EXTRA_ROUTINE_NAME,routineName);
                GymTrackr.getContext().startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerView(routineName);
    }

    private void loadRecyclerView(String routineName) {
        List<String> exercises  = DomainController.getInstance().getAssignedExercises(routineName);

        ExercisesAdapter exercisesAdapter = new ShowAssignedExercisesAdapter(exercises,routineName);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
    }


}
