package com.gymtrackr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Domain.Routine;

import java.util.List;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.MyViewHolder> {

    private List<String> routinesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvDayOfTheWeek;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.name);
            tvDayOfTheWeek = view.findViewById(R.id.dayOfTheWeek);
        }
    }

    public RoutinesAdapter(List<String> routinesList) {
        super();
        this.routinesList = routinesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String routineName = routinesList.get(position);
        holder.tvName.setText(routineName);

        System.out.println("ROUTINE: " + routineName);

        List<String> rawRoutine = DomainController.getInstance().getRoutineInformation(routineName);
        String[] daysOfTheWeek = GymTrackr.getContext().getResources().getStringArray(R.array.days_of_the_week_array);
        holder.tvDayOfTheWeek.setText(daysOfTheWeek[Integer.valueOf(rawRoutine.get(0))]);
    }

    @Override
    public int getItemCount() {
        return routinesList.size();
    }
}
