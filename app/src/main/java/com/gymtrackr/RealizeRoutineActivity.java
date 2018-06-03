package com.gymtrackr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.gymtrackr.Domain.DomainController;

import java.util.List;

public class RealizeRoutineActivity extends AppCompatActivity {

    public static String EXTRA_ROUTINE_NAME = "routineName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_routine);

        String routineName = new String();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) routineName = bundle.getString(EXTRA_ROUTINE_NAME);

        List<String> exerciseList = DomainController.getInstance().startRoutine(routineName);

        RealizeRoutineAdapter startRoutineAdapter = new RealizeRoutineAdapter(exerciseList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(startRoutineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Button bFinish = findViewById(R.id.bFinish);
        bFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DomainController.getInstance().finishRoutine();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> exerciseList = DomainController.getInstance().getRemainExercises();

        if(exerciseList.isEmpty()) {
            DomainController.getInstance().finishRoutine();
            finish();
        }

        RealizeRoutineAdapter startRoutineAdapter = new RealizeRoutineAdapter(exerciseList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(startRoutineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DomainController.getInstance().finishRoutine();
    }

}
