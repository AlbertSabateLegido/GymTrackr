package com.gymtrackr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.MyViewHolder>{

    public static String SHOW = "show";
    public static String ASSIGN = "assign";

    private List<String> exerciseList;
    private String type;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, cbAssign;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.name);
            cbAssign = view.findViewById(R.id.cbAssign);
        }
    }

    public ExercisesAdapter(List<String> exerciseList,String type) {
        super();
        this.exerciseList = exerciseList;
        this.type = type;
        System.out.println("HOLA!");
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

        if(type.equals(SHOW)) {
            holder.cbAssign.setVisibility(View.GONE);
        }

        if(type.equals(ASSIGN)) {
            //Do something
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

}
