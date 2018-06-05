package com.gymtrackr;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Domain.Exercise;

import java.util.List;

public class ShowAssignedExercisesAdapter extends ExercisesAdapter {

    String routineName;

    public ShowAssignedExercisesAdapter(List<String> exerciseList,String routineName) {
        super(exerciseList, ExercisesAdapter.SHOW);
        this.routineName = routineName;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (position == 0) {
            holder.tvName.setText("Start Routine");
            holder.ivAction.setImageDrawable(GymTrackr.getContext().getResources().
                    getDrawable(R.drawable.ic_action_workout));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.
                    LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(16);
            holder.ivAction.setLayoutParams(layoutParams);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GymTrackr.getContext(),RealizeRoutineActivity.class);
                    intent.putExtra(RealizeRoutineActivity.EXTRA_ROUTINE_NAME,routineName);
                    GymTrackr.getContext().startActivity(intent);
                }
            });
        }
        else if (position == 1) {
            holder.tvName.setText("Add Exercises");
            holder.ivAction.setImageDrawable(GymTrackr.getContext().getResources().
                    getDrawable(R.drawable.ic_action_link));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.
                    LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(16);
            holder.ivAction.setLayoutParams(layoutParams);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GymTrackr.getContext(),EditAssignedExercisesActivity.class);
                    intent.putExtra(EditAssignedExercisesActivity.EXTRA_ROUTINE_NAME,routineName);
                    GymTrackr.getContext().startActivity(intent);
                }
            });
        }
        else {
            super.onBindViewHolder(holder,position-2);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GymTrackr.getContext(),ShowExerciseActivity.class);
                    Exercise selectedExercise = DomainController.getInstance().getExerciseList().get(position-2);
                    intent.putExtra(ShowExerciseActivity.EXTRA_EXERCISE, position-2);
                    GymTrackr.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int listSize = super.getItemCount();
        return listSize+2;
    }
}
