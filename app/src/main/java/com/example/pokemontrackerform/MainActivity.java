package com.example.pokemontrackerform;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;
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
    Button resetButton, saveButton;

    //editable texts
    EditText nationalNumber, name, species, height, weight, hp, attack, defense;

    //radio group
    RadioGroup rGroup;

    //text views (labels)
    TextView levelLabel, nationalNumLabel, nameLabel, speciesLabel, genderLabel, heightLabel, weightLabel, hpLabel, attackLabel, defenseLabel;
    //strings
    String selectedLevel;

    //level select listener
    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedLevel = parent.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);
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
        saveButton = findViewById(R.id.saveButton);
        nameLabel = findViewById(R.id.nameLabel);
        hpLabel = findViewById(R.id.hpLabel);
        attackLabel = findViewById(R.id.attackLabel);
        defenseLabel = findViewById(R.id.defenseLabel);
        heightLabel = findViewById(R.id.heightLabel);
        weightLabel = findViewById(R.id.weightLabel);
        genderLabel = findViewById(R.id.GenderLabel);
        levelLabel = findViewById(R.id.levelLabel);
        nationalNumLabel = findViewById(R.id.nationalNumberLabel);
        speciesLabel = findViewById(R.id.speciesLabel);

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

                nameLabel.setTextColor(Color.BLACK);
                hpLabel.setTextColor(Color.BLACK);
                attackLabel.setTextColor(Color.BLACK);
                defenseLabel.setTextColor(Color.BLACK);
                heightLabel.setTextColor(Color.BLACK);
                weightLabel.setTextColor(Color.BLACK);
                genderLabel.setTextColor(Color.BLACK);
                levelLabel.setTextColor(Color.BLACK);
            nationalNumLabel.setTextColor(Color.BLACK);
                speciesLabel.setTextColor(Color.BLACK);
            }
        });

        //saveButton
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correct = true;
                StringBuilder errors = new StringBuilder();

                //set all labels to black
                nameLabel.setTextColor(Color.BLACK);
                hpLabel.setTextColor(Color.BLACK);
                attackLabel.setTextColor(Color.BLACK);
                defenseLabel.setTextColor(Color.BLACK);
                heightLabel.setTextColor(Color.BLACK);
                weightLabel.setTextColor(Color.BLACK);
                genderLabel.setTextColor(Color.BLACK);
                levelLabel.setTextColor(Color.BLACK);
                nationalNumLabel.setTextColor(Color.BLACK);
                speciesLabel.setTextColor(Color.BLACK);

                //checks to make sure nothing is empty
                if (nationalNumber.getText().toString().isEmpty()) {
                    correct = false;
                    nationalNumLabel.setTextColor(Color.RED);
                    errors.append("Must input something into  national number.\n");
                }
                if (name.getText().toString().isEmpty()) {
                    correct = false;
                    nameLabel.setTextColor(Color.RED);
                    errors.append("Name cannot be empty.\n"); }
                if (species.getText().toString().isEmpty()) {
                    correct = false;
                    speciesLabel.setTextColor(Color.RED);
                    errors.append("Must input something into species.\n");
                }
                if (hp.getText().toString().isEmpty()) {
                    correct = false;
                    hpLabel.setTextColor(Color.RED);
                    errors.append("HP cannot be empty.\n");
                }
                if (attack.getText().toString().isEmpty()) {
                    correct = false;
                    attackLabel.setTextColor(Color.RED);
                    errors.append("Attack cannot be empty.\n");
                }
                if (defense.getText().toString().isEmpty()) {
                    correct = false;
                    defenseLabel.setTextColor(Color.RED);
                    errors.append("Defense cannot be empty.\n");
                }
                if (height.getText().toString().isEmpty()) {
                    correct = false;
                    heightLabel.setTextColor(Color.RED);
                    errors.append("Height cannot be empty.\n");
                }
                if (weight.getText().toString().isEmpty()) {
                    correct = false;
                    weightLabel.setTextColor(Color.RED);
                    errors.append("Weight cannot be empty.\n");
                }
                int selectedGenderId = rGroup.getCheckedRadioButtonId();
                if (selectedGenderId == -1) {
                    correct = false;
                    genderLabel.setTextColor(Color.RED);
                    errors.append("Gender must be selected.\n"); }
                if (selectedLevel.equals(levels.get(0))) {
                    correct = false;
                    levelLabel.setTextColor(Color.RED);
                    errors.append("Levels needs to be selected.\n");
                }



                //requirements
                String nameText = name.getText().toString().trim();
                for (int i = 0; i < nameText.length(); i++) {
                    char c = nameText.charAt(i);
                    if (!(Character.isLetter(c) || c == ' ' || c == '.')) {
                        correct = false;
                        nameLabel.setTextColor(Color.RED);
                        errors.append("Name can only be letters, dots, or white space.");
                        break;
                    }
                }
                if (correct) {
                    // capitalize words
                    String[] words = nameText.split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String w : words) {
                        if (!w.isEmpty()) {
                            sb.append(Character.toUpperCase(w.charAt(0)))
                                    .append(w.substring(1).toLowerCase())
                                    .append(" ");
                        }
                    }
                    name.setText(sb.toString().trim());
                }

                String speciesText = species.getText().toString().trim();
                for (int i = 0; i < speciesText.length(); i++) {
                    char c = speciesText.charAt(i);
                    if (!(Character.isLetter(c) || c == ' ')) {
                        correct = false;
                        speciesLabel.setTextColor(Color.RED);
                        errors.append("Species can only be letters and white space.");
                        break;
                    }
                }
                if (correct) {
                    // capitalize words
                    String[] words = speciesText.split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String w : words) {
                        if (!w.isEmpty()) {
                            sb.append(Character.toUpperCase(w.charAt(0)))
                                    .append(w.substring(1).toLowerCase())
                                    .append(" ");
                        }
                    }
                    species.setText(sb.toString().trim());
                }

                int nationalNumValue = Integer.parseInt(nationalNumber.getText().toString());
                if(nationalNumValue < 0 || nationalNumValue > 1010) {
                    correct = false;
                    nationalNumLabel.setTextColor(Color.RED);
                    errors.append("National number must be between 0 and 1010.");
                }

                int hpValue = Integer.parseInt(hp.getText().toString());
                if (hpValue < 1 || hpValue > 362) {
                    correct = false;
                    hpLabel.setTextColor(Color.RED);
                    errors.append("HP must be 1–362.\n"); }

                int attackValue = Integer.parseInt(attack.getText().toString());
                if (attackValue < 0 || attackValue > 526) {
                    correct = false;
                    attackLabel.setTextColor(Color.RED);
                    errors.append("Attack must be 0–526.\n"); }

                int defenseValue = Integer.parseInt(defense.getText().toString());
                if (defenseValue < 10 || defenseValue > 614) {
                    correct = false;
                    defenseLabel.setTextColor(Color.RED);
                    errors.append("Defense must be 10–614.\n"); }

                String heightStr = height.getText().toString();
                if (heightStr.endsWith("m")) {
                    heightStr = heightStr.substring(0, heightStr.length() - 1);
                }
                double heightValue = Double.parseDouble(heightStr);
                if (heightValue < 0.2 || heightValue > 169.99 || heightStr.contains(".") && heightStr.substring(heightStr.indexOf(".") + 1).length() > 2) {
                    correct = false;
                    heightLabel.setTextColor(Color.RED);
                    errors.append("Height must be 0.2–169.99.\n"); }
                heightStr = heightStr + "m";
                height.setText(heightStr);

                String weightStr = weight.getText().toString();
                if (weightStr.endsWith("Kg")) {
                    weightStr = weightStr.substring(0, weightStr.length() - 2);
                }
                double weightValue = Double.parseDouble(weightStr);
                if (weightValue < 0.1 || weightValue > 992.7 || weightStr.contains(".") && weightStr.substring(weightStr.indexOf(".") + 1).length() > 2) {
                    correct = false;
                    weightLabel.setTextColor(Color.RED);
                    errors.append("Weight must be 0.1–992.7 and two decimals.\n"); }
                weightStr = weightStr + "Kg";
                weight.setText(weightStr);

                //toast and show errors
                if (correct) {
                    Toast.makeText(MainActivity.this, "Information stored in database (not really lol)!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, errors.toString(), Toast.LENGTH_LONG).show();
                }



            }
        });
    }
}