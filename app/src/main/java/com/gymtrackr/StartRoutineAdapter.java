package com.gymtrackr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;

import java.util.List;

public class StartRoutineAdapter extends RecyclerView.Adapter<StartRoutineAdapter.MyViewHolder> {

    List<String> assignedExercises;

    public StartRoutineAdapter(List<String> assignedExercises) {
        super();
        this.assignedExercises = assignedExercises;
        System.out.println("Size: " + assignedExercises.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvRepetitionsXSeries,tvWeight;
        Button bDone;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.name);
            tvRepetitionsXSeries = view.findViewById(R.id.repetitions_series);
            tvWeight = view.findViewById(R.id.weight);
            bDone = view.findViewById(R.id.bDone);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_exercise_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String exerciseName = assignedExercises.get(position);
        List<String> rawExerciseInformation = DomainController.getInstance().getExerciseInformation(exerciseName);
        holder.tvName.setText(exerciseName);
        String concatString = rawExerciseInformation.get(0) + "x" + rawExerciseInformation.get(1);
        holder.tvRepetitionsXSeries.setText(concatString);
        concatString = rawExerciseInformation.get(2) + "kg";
        holder.tvWeight.setText(concatString);
    }

    @Override
    public int getItemCount() {
        return assignedExercises.size();
    }
}
