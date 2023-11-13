package com.example.bidbound.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidbound.MainActivity;
import com.example.bidbound.R;

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