package com.gymtrackr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class AddRoutine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);
        getSupportActionBar().setTitle(R.string.title_add_routine);
        Spinner spinnerDay = findViewById(R.id.spinnerDay);
        ArrayList<String> dayList = new ArrayList<>();
        dayList.add(getString(R.string.days_none));
        dayList.add(getString(R.string.days_monday));
        dayList.add(getString(R.string.days_tuesday));
        dayList.add(getString(R.string.days_wednesday));
        dayList.add(getString(R.string.days_thursday));
        dayList.add(getString(R.string.days_friday));
        dayList.add(getString(R.string.days_saturday));
        dayList.add(getString(R.string.days_sunday));
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dayList);
        spinnerDay.setAdapter(myAdapter);
    }
}
