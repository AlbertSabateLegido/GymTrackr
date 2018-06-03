package com.gymtrackr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.gymtrackr.Domain.DomainController;

import java.util.ArrayList;
import java.util.List;

public class EditAssignedExercisesActivity extends AppCompatActivity {

    public static String EXTRA_ROUTINE_NAME = "routine_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assigned_exercises);

        String routineName = new String();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) routineName = bundle.getString(EXTRA_ROUTINE_NAME);

        List<String> exercises = DomainController.getInstance().getExerciseNames();
        List<String> assignedExercises = DomainController.getInstance().getAssignedExercises(routineName);

        final EditAssignedExercisesAdapter exercisesAdapter = new EditAssignedExercisesAdapter(exercises,assignedExercises);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));

        Button bCancel = findViewById(R.id.bCancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button bAccept = findViewById(R.id.bAccept);
        final String finalRoutineName = routineName;
        bAccept.setOnClickListener(new View.OnClickListener() {
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
                DomainController.getInstance().deleteAssignedExercises(finalRoutineName);
                DomainController.getInstance().assignExercises(finalRoutineName,assignedExercisesList);
                finish();
            }
        });
    }
}
