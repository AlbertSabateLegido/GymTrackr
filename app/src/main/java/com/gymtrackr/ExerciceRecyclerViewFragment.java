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
import com.gymtrackr.Domain.Exercise;
import com.gymtrackr.Domain.ExerciseList;

import java.util.ArrayList;
import java.util.List;

public class ExerciceRecyclerViewFragment extends Fragment {
    private ExerciseList exerciseList;
    private ExercicesAdapter exercicesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DomainController domainController = DomainController.getInstance();
        exerciseList = domainController.getExerciseList();
        /*exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Exercice 1"));
        exerciseList.add(new Exercise("Exercice 2"));
        exerciseList.add(new Exercise("Exercice 3"));
        exerciseList.add(new Exercise("Bench press"));
        exerciseList.get(3).setReps(10);
        exerciseList.get(3).setSeries(4);*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        exercicesAdapter = new ExercicesAdapter(exerciseList);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercicesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getActivity().getApplicationContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        exercicesAdapter.notifyDataSetChanged();
    }
}
