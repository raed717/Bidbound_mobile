package com.example.bidbound.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bidbound.Projects.NewProject;
import com.example.bidbound.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProjectsActivity extends AppCompatActivity {

    private ImageButton ProjectDetails,AddProjectButton,deleteProjectButton;
    private TextView dataTextView;
    private EditText projectNameToDeleteField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        AddProjectButton = (ImageButton) findViewById(R.id.AddProjectB);
        ProjectDetails = (ImageButton) findViewById(R.id.projectDetails);
        deleteProjectButton = (ImageButton) findViewById(R.id.deleteProjectButton);

        projectNameToDeleteField = findViewById(R.id.projectNameToDeleteField);
        dataTextView = findViewById(R.id.projectsDataView);

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

        deleteProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectNameToDelete = projectNameToDeleteField.getText().toString();
                deleteProject(projectNameToDelete);
            }
        });


        String data = readDataFromFile();
        String formattedData = formatData(data);
        if (data != null) {
            // Set the data to the TextView
            dataTextView.setText(formattedData);
        } else {
            // Handle the case where there is no data
            dataTextView.setText("No data found");
        }

    }

    public void openProjectDetails(){
        Intent intent = new Intent(this, com.example.bidbound.Projects.ProjectDetails.class);
        startActivity(intent);
    }
    public void openAddProject(){
        Intent intent = new Intent(this, NewProject.class);
        startActivity(intent);
    }
    private String readDataFromFile() {
        try {
            FileInputStream fis = openFileInput("project_data.txt");
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            fis.close();
            if (bytesRead > 0) {
                return new String(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String formatData(String rawData) {
        if (rawData == null) {
            return "No data found";
        }

        StringBuilder formattedData = new StringBuilder();
        String[] projects = rawData.split("\n");

        for (String project : projects) {
            String[] projectDetails = project.split(",");
            if (projectDetails.length >= 3) { // Ensure there are enough fields
                String projectName = projectDetails[0];
                String projectSubject = projectDetails[1];
                String selectedDate = projectDetails[2];

                formattedData.append("Project Name: ").append(projectName).append("\n");
                formattedData.append("Project Subject: ").append(projectSubject).append("\n");
                formattedData.append("Selected Date: ").append(selectedDate).append("\n\n");
            }
        }
        return formattedData.toString();
    }
    private void deleteProject(String projectNameToDelete) {
        try {
            FileInputStream fis = openFileInput("project_data.txt");
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            fis.close();

            if (bytesRead > 0) {
                String existingData = new String(buffer, 0, bytesRead);

                String[] projects = existingData.split("\n");
                StringBuilder updatedData = new StringBuilder();

                boolean projectDeleted = false;

                for (String project : projects) {
                    String[] projectDetails = project.split(",");
                    if (projectDetails.length >= 1 && projectDetails[0] != null
                            && !projectDetails[0].equals(projectNameToDelete)) {
                        updatedData.append(project).append("\n");
                    } else {
                        // Project found and deleted
                        projectDeleted = true;
                    }
                }

                if (!projectDeleted) {
                    Toast.makeText(this, "Project not found", Toast.LENGTH_SHORT).show();
                } else {
                    FileOutputStream fos = openFileOutput("project_data.txt", Context.MODE_PRIVATE);
                    fos.write(updatedData.toString().getBytes());
                    fos.close();

                    Toast.makeText(this, "Project deleted successfully", Toast.LENGTH_SHORT).show();

                    // Update your UI components if needed
                    String updatedDataString = readDataFromFile();
                    dataTextView.setText(formatData(updatedDataString));
                }
            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

}


