package com.gymtrackr;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RoutineRecyclerViewFragment extends Fragment {

    private List<Routine> routinesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        routinesList = new ArrayList<>();
        routinesList.add(new Routine("Routine 1"));
        routinesList.add(new Routine("Routine 2"));
        routinesList.add(new Routine("Routine 3"));
        System.out.println("RoutineRecyclerViewFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RoutinesAdapter routinesAdapter = new RoutinesAdapter(routinesList);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(routinesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getActivity().getApplicationContext()));

        return view;
    }
}
