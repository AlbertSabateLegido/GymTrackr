package com.gymtrackr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gymtrackr.Domain.DomainController;

public class EditNameActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        String name = new String();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) name = bundle.getString(EXTRA_NAME);

        final EditText etName = findViewById(R.id.etName);
        etName.setText(name);

        Button bCancel = findViewById(R.id.bCancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button bAccept = findViewById(R.id.bAccept);
        final String finalName = name;
        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = etName.getText().toString();
                if(finalName.equals(newName)) finish();
                DomainController.getInstance().setRoutineName(finalName,newName);
                Intent intent = new Intent(GymTrackr.getContext(),ShowRoutineActivity.class);
                intent.putExtra(ShowRoutineActivity.EXTRA_ROUTINE_NAME,newName);
                startActivity(intent);
            }
        });
    }
}
