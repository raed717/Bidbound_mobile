package com.example.bidbound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProjectsActivity extends AppCompatActivity {

    private ImageButton ProjectDetails;
    private ImageButton AddProjectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        ProjectDetails = (ImageButton) findViewById(R.id.DetailsButton);
        AddProjectButton = (ImageButton) findViewById(R.id.AddProjectB);

        ProjectDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProjectDetails();
            }
        });
        AddProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddProject();
            }
        });
    }

    public void openProjectDetails(){
        Intent intent = new Intent(this, ProjectDetails.class);
        startActivity(intent);
    }
    public void openAddProject(){
        Intent intent = new Intent(this, NewProject.class);
        startActivity(intent);
    }
}