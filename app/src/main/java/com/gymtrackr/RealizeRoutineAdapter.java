package com.gymtrackr;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;

import java.util.List;

public class RealizeRoutineAdapter extends RecyclerView.Adapter<RealizeRoutineAdapter.MyViewHolder> {

    List<String> assignedExercises;

    public RealizeRoutineAdapter(List<String> assignedExercises) {
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
        final String exerciseName = assignedExercises.get(position);
        holder.tvName.setText(exerciseName);
        List<String> rawExerciseInformation = DomainController.getInstance().getExerciseInformation(exerciseName);
        if(rawExerciseInformation.isEmpty()) {
            holder.tvRepetitionsXSeries.setText(R.string.label_no_data);
        }
        else {
            String concatString = rawExerciseInformation.get(0) + "x" + rawExerciseInformation.get(1);
            holder.tvRepetitionsXSeries.setText(concatString);
            concatString = rawExerciseInformation.get(2) + "kg";
            holder.tvWeight.setText(concatString);
        }
        holder.bDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GymTrackr.getContext(),RealizeExercise.class);
                intent.putExtra(RealizeExercise.EXTRA_EXERCISE_NAME,exerciseName);
                GymTrackr.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignedExercises.size();
    }
}
