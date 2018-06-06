package com.gymtrackr;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.gymtrackr.Domain.DomainController;

import java.util.ArrayList;
import java.util.List;

public class AssignExercisesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_exercises);

        List<String> exerciseList = DomainController.getInstance().getExerciseNames();

        final ExercisesAdapter exercisesAdapter = new ExercisesAdapter(exerciseList,ExercisesAdapter.ASSIGN);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> assignedExercisesList = new ArrayList<>();

                for(int i = 0; i < exercisesAdapter.getItemCount(); ++i) {
                    CheckBox cbAssign = recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.cbAssign);
                    if(cbAssign.isChecked())
                        assignedExercisesList.add(exercisesAdapter.getItem(i));
                }

                if(assignedExercisesList.isEmpty()) {
                    Toast.makeText(GymTrackr.getContext(), GymTrackr.getContext().getResources().
                                    getString(R.string.assigned_list_empty), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(GymTrackr.getContext(), AddRoutineActivity.class);
                intent.putStringArrayListExtra(AddRoutineActivity.ASSIGNED_EXERCISES,assignedExercisesList);
                startActivity(intent);
            }
        });
    }
}
