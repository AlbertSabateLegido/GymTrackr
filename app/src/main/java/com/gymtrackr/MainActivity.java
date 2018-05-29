package com.gymtrackr;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.gymtrackr.Persistence.PersistenceManager;
import com.gymtrackr.Persistence.PersistenceManagerImpl;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private enum TabSelected {ROUTINES,EXERCISES};
    TabSelected tabSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,new RoutineRecyclerViewFragment());
        ft.commit();
        tabSelected = TabSelected.ROUTINES;

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(this);

        final Intent intentAddRoutine = new Intent(this, AddRoutineActivity.class);
        final Intent intentAddExercise = new Intent(this, AddExerciseActivity.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tabSelected == TabSelected.ROUTINES){
                    startActivity(intentAddRoutine);
                }
                else if (tabSelected == TabSelected.EXERCISES){
                    startActivity(intentAddExercise);
                }
            }
        });

        PersistenceManager persistenceManager = new PersistenceManagerImpl(getApplicationContext());
        persistenceManager.getRoutines();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition() == 0) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container,new RoutineRecyclerViewFragment());
            ft.commit();
            tabSelected = TabSelected.ROUTINES;
        }
        if(tab.getPosition() == 1) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container,new ExerciseRecyclerViewFragment());
            ft.commit();
            tabSelected = TabSelected.EXERCISES;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
