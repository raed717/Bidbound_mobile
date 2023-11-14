package com.example.bidbound.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bidbound.R;
import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.entities.Project;

import java.util.concurrent.Executors;

public class ProjectDetails extends AppCompatActivity {

    private EditText editTextProjectName, editTextProjectCategory;
    private Button saveButton;
    private ImageButton editButton;
    private Project currentProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        editButton = (ImageButton) findViewById(R.id.editButton);
        editTextProjectName = findViewById(R.id.editTextProjectName); // Replace with actual ID
        editTextProjectCategory = findViewById(R.id.editTextProjectCategory); // Replace with actual ID
        saveButton = findViewById(R.id.saveButton); // Replace with actual ID

        int projectId = getIntent().getIntExtra("projectId", -1);
        if (projectId != -1) {
            loadProjectData(projectId);
        }

        saveButton.setOnClickListener(v -> saveProject());
    }

    private void loadProjectData(int projectId) {
        Executors.newSingleThreadExecutor().execute(() -> {
            currentProject = AppDatabase.getAppDatabase(getApplicationContext()).projectDAO().getProjectById(projectId);
            runOnUiThread(() -> {
                if (currentProject != null) {
                    editTextProjectName.setText(currentProject.getProjectName());
                    editTextProjectCategory.setText(currentProject.getProjectCategory());
                    // Set other fields as needed
                }
            });
        });
    }

    // Method to save the edited project data back to the database
    private void saveProject() {
        String projectName = editTextProjectName.getText().toString();
        String projectCategory = editTextProjectCategory.getText().toString();
        // Retrieve other data from EditTexts as needed

        if (currentProject != null) {
            currentProject.setProjectName(projectName);
            currentProject.setProjectCategory(projectCategory);
            // Set other attributes as needed

            Executors.newSingleThreadExecutor().execute(() -> {
                AppDatabase.getAppDatabase(getApplicationContext()).projectDAO().updateProject(currentProject);
                runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), "Project updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and go back
                });
            });
        }
    }
}