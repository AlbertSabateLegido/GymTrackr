package com.gymtrackr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gymtrackr.Domain.DomainController;
import com.gymtrackr.Domain.ExerciseList;

public class ExerciseRecyclerViewFragment extends Fragment {
    private ExerciseList exerciseList;
    private ExercisesAdapter exercisesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DomainController domainController = DomainController.getInstance();
        exerciseList = domainController.getExerciseList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        exercisesAdapter = new ExercisesAdapter(exerciseList);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getActivity().getApplicationContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        exercisesAdapter.notifyDataSetChanged();
    }
}
