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

public class RoutinesListFragment extends Fragment {

    private List<Routine> routinesList;
    private RoutinesAdapter routinesAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        routinesList = new ArrayList<>();
        routinesList.add(new Routine("Routine 1"));
        routinesList.add(new Routine("Routine 2"));
        routinesList.add(new Routine("Routine 3"));
        System.out.println("RoutinesListFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routines_list, container, false);

        recyclerView = view.findViewById(R.id.rvRoutines);

        routinesAdapter = new RoutinesAdapter(routinesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(routinesAdapter);

        routinesList.add(new Routine("New Routine"));

        routinesAdapter.notifyDataSetChanged();

        return view;
    }
}