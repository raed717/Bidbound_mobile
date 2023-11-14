package com.example.bidbound.Projects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.bidbound.MainActivity;
import com.example.bidbound.R;
import com.example.bidbound.SignUpActivity;
import com.example.bidbound.activity.addTeam;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.bidbound.MainActivity;

public class ProjectMenuActivity extends AppCompatActivity {

    private Button allprojectsButton;
    private Button HomeButton;
    private Button NewProjectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_menu);
        allprojectsButton = (Button) findViewById(R.id.allprojects_button);
        HomeButton = (Button) findViewById(R.id.home_button);
        NewProjectButton = (Button) findViewById(R.id.newproject_button);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.bottom_dec) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_left);
                            finish();
                            return true;



                    } else if (item.getItemId() == R.id.bottom_equipe) {
                            startActivity(new Intent(getApplicationContext(), addTeam.class));
                            overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_left);
                            finish();
                            return true;
                    }
                        return true;
                    }
                });







        allprojectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProjectsActivity();
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });

        NewProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewProject();
            }
        });
    }
    public void openProjectsActivity(){
        Intent intent = new Intent(this, ProjectsActivity.class);
        startActivity(intent);
    }
    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openNewProject(){
        Intent intent = new Intent(this, NewProject.class);
        startActivity(intent);
    }
}