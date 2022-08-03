package com.example.puzzle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.puzzle.MapsTreatment;
import com.example.puzzle.R;

public class AnimalDiseases extends AppCompatActivity {
    Toolbar toolbar;
    TextView diseases, treatment, symptoms, name_animal;
    ImageView animal_img_choose, loca_treat, loca_dis;
    String DISEASES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animal_diseases);
        setToolbar();
        loca_treat = findViewById(R.id.loca_treat);
        loca_dis = findViewById(R.id.loca_dis);

        diseases = findViewById(R.id.disease_);
        treatment = findViewById(R.id.treatment_);
        symptoms = findViewById(R.id.symptoms_);
        animal_img_choose = findViewById(R.id.animal_img_choose);
        name_animal = findViewById(R.id.txt_name_of_animal);

        Intent intent = getIntent();
        treatment.setText(intent.getStringExtra("treatment"));
        diseases.setText(intent.getStringExtra("diseases"));
        symptoms.setText(intent.getStringExtra("symptoms"));
        animal_img_choose.setImageResource(intent.getIntExtra("img_animal", 0));
        name_animal.setText(intent.getStringExtra("name_animal"));

        DISEASES = intent.getStringExtra("diseases");

        loca_treat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalDiseases.this, MapsTreatment.class);
                startActivity(intent);

            }
        });

        loca_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalDiseases.this, MapsDiseases.class);
                intent.putExtra("diseases", DISEASES);
                startActivity(intent);
            }
        });


    }

    private void setToolbar() {
        toolbar = findViewById(R.id.tool_fav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}