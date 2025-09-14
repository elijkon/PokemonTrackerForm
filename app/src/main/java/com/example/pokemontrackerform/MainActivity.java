package com.example.pokemontrackerform;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //spinner
    Spinner levelSpinner;

    //buttons
    Button resetButton;

    //editable texts
    EditText nationalNumber, name, species, height, weight, hp, attack, defense;

    //radio group
    RadioGroup rGroup;



    //level select listener
    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedLevelType = parent.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //populating levels array
        List<String> levels = new ArrayList<>();
        levels.add("N/A");
        for (int i = 1; i <= 50 ; i ++) {
            levels.add(String.valueOf(i));
        }

        //getting ids
        levelSpinner = findViewById(R.id.levelSpinner);
        resetButton = findViewById(R.id.resetButton);
        nationalNumber = findViewById(R.id.nationalNumberInput);
        name = findViewById(R.id.nameInput);
        species = findViewById(R.id.speciesInput);
        height = findViewById(R.id.heightInput);
        weight = findViewById(R.id.weightInput);
        hp = findViewById(R.id.hpInput);
        attack = findViewById(R.id.attackInput);
        defense = findViewById(R.id.defenseInput);
        rGroup = findViewById(R.id.genderGroup);

        //adapter for levels spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                levels
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter);
        levelSpinner.setOnItemSelectedListener(spinnerListener);

        //resetButton
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack.setText(getString(R.string.defaultBaseStats));
                nationalNumber.setText(getString(R.string._896));
                name.setText(getString(R.string.glastrier));
                species.setText(getString(R.string.wild_horse_pokemon));
                height.setText(getString(R.string.defaultMeters));
                weight.setText(getString(R.string.defaultKg));
                hp.setText(getString(R.string.defaultBaseStats));
                defense.setText(getString(R.string.defaultBaseStats));
                rGroup.clearCheck();
                levelSpinner.setSelection(0);
            }
        });
    }
}