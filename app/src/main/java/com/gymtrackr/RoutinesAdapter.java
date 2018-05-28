package com.gymtrackr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.MyViewHolder> {

    private List<Routine> routinesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvDayOfTheWeek;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.name);
            tvDayOfTheWeek = view.findViewById(R.id.dayOfTheWeek);
        }
    }

    public RoutinesAdapter(List<Routine> routinesList) {
        this.routinesList = routinesList;
        System.out.println("RoutinesAdapter");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine_row, parent, false);

        System.out.println("OnCreateViewHolder");

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Routine routine = routinesList.get(position);
        holder.tvName.setText(routine.getName());
        holder.tvDayOfTheWeek.setText(routine.getDayOfTheWeek().toString());

        System.out.println("OnBindViewHolder, " + position);
    }

    @Override
    public int getItemCount() {
        return routinesList.size();
    }
}
