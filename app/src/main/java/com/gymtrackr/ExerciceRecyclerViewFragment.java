package com.gymtrackr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ExerciceRecyclerViewFragment extends Fragment {
    private List<Exercise> exerciseList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Exercice 1"));
        exerciseList.add(new Exercise("Exercice 2"));
        exerciseList.add(new Exercise("Exercice 3"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        ExercicesAdapter exercicesAdapter = new ExercicesAdapter(exerciseList);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exercicesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getActivity().getApplicationContext()));

        return view;
    }
}
