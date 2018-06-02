package com.gymtrackr;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

public class ShowAssignedExercisesAdapter extends ExercisesAdapter {

    public ShowAssignedExercisesAdapter(List<String> exerciseList) {
        super(exerciseList, ExercisesAdapter.SHOW);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (position == 0) {
            holder.tvName.setText("Start Routine");
            holder.ivAction.setImageDrawable(GymTrackr.getContext().getResources().
                    getDrawable(R.drawable.ic_action_workout));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.
                    LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(16);
            holder.ivAction.setLayoutParams(layoutParams);
        }
        else if (position == 1) {
            holder.tvName.setText("Add Exercises");
            holder.ivAction.setImageDrawable(GymTrackr.getContext().getResources().
                    getDrawable(R.drawable.ic_action_link));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.
                    LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(16);
            holder.ivAction.setLayoutParams(layoutParams);
        }
        else super.onBindViewHolder(holder,position-2);
    }

    @Override
    public int getItemCount() {
        int listSize = super.getItemCount();
        return listSize+2;
    }
}
