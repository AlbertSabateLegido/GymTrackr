package com.gymtrackr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gymtrackr.Domain.Routine;

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
        Routine routine = routinesList.get(position);
        holder.tvName.setText(routine.getName());
        holder.tvDayOfTheWeek.setText(routine.getDayOfTheWeek().toString());
        /*
        switch (routine.getDayOfTheWeek()){
            case NONE:
                holder.tvDayOfTheWeek.setText(R.string.days_none);
                break;
            case MONDAY:
                holder.tvDayOfTheWeek.setText(R.string.days_monday);
                break;
            case TUESDAY:
                holder.tvDayOfTheWeek.setText(R.string.days_tuesday);
                break;
            case WEDNESDAY:
                holder.tvDayOfTheWeek.setText(R.string.days_wednesday);
                break;
            case THURSDAY:
                holder.tvDayOfTheWeek.setText(R.string.days_thursday);
                break;
            case FRIDAY:
                holder.tvDayOfTheWeek.setText(R.string.days_friday);
                break;
            case SATURDAY:
                holder.tvDayOfTheWeek.setText(R.string.days_saturday);
                break;
            case SUNDAY:
                holder.tvDayOfTheWeek.setText(R.string.days_sunday);
                break;
            default:
                holder.tvDayOfTheWeek.setText(R.string.days_none);
                break;
        } */
    }

    @Override
    public int getItemCount() {
        return routinesList.size();
    }
}
