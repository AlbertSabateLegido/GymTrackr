package com.gymtrackr;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Domain.Exercise;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.MyViewHolder>{

    public static String SHOW = "show";
    public static String ASSIGN = "assign";

    protected List<String> exerciseList;
    private String type;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        public CheckBox cbAssign;
        public ImageView ivAction;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.name);
            cbAssign = view.findViewById(R.id.cbAssign);
            ivAction = view.findViewById(R.id.actionImage);
            this.view = view;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(GymTrackr.getContext(),ShowExerciseActivity.class);
            String selectedExercise = tvName.getText().toString();
            intent.putExtra(ShowExerciseActivity.EXTRA_EXERCISE, selectedExercise);
            GymTrackr.getContext().startActivity(intent);
        }
    }

    public ExercisesAdapter(List<String> exerciseList,String type) {
        super();
        this.exerciseList = exerciseList;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_row, parent, false);

        return new ExercisesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String exerciseName = exerciseList.get(position);

        holder.tvName.setText(exerciseName);

        if(type.equals(ASSIGN)) {
            holder.cbAssign.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public String getItem(int position) {
        return exerciseList.get(position);
    }

    public void addExercise(String name) {
        exerciseList.add(name);
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }

}
