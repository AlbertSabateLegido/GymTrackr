package com.gymtrackr;

import android.view.View;

import java.util.List;

public class EditAssignedExercisesAdapter extends ExercisesAdapter {

    List<String> assignedExercises;

    public EditAssignedExercisesAdapter(List<String> exerciseList, List<String> assignedExercises) {
        super(exerciseList, ExercisesAdapter.ASSIGN);
        this.assignedExercises = assignedExercises;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(assignedExercises.contains(exerciseList.get(position))) {
            System.out.println("ASSIGNED EXERCISE: " + exerciseList.get(position));
            holder.cbAssign.setVisibility(View.VISIBLE);
            holder.cbAssign.setChecked(true);
        }
    }

    public List<String> getAssignedExercises() {
        return assignedExercises;
    }
}
