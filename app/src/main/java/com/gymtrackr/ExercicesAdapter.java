package com.gymtrackr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ExercicesAdapter extends RecyclerView.Adapter<ExercicesAdapter.MyViewHolder>{

    List<Exercise> exerciseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvRepetitions,tvSeries;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.name);
            tvRepetitions = view.findViewById(R.id.repetitions);
            tvSeries = view.findViewById(R.id.series);
        }
    }

    public ExercicesAdapter(List<Exercise> exerciseList) {
        super();
        this.exerciseList = exerciseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_row, parent, false);

        return new ExercicesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);

        holder.tvName.setText(exercise.getName());
        holder.tvRepetitions.setText(String.valueOf(exercise.getReps()));
        holder.tvSeries.setText(String.valueOf(exercise.getSeries()));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }


}
