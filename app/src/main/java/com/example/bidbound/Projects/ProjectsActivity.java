package com.example.bidbound.Projects;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bidbound.Projects.NewProject;
import com.example.bidbound.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectsActivity extends AppCompatActivity {

    private ImageButton ProjectDetails,AddProjectButton, searchButton;
    private TextView dataTextView;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        AddProjectButton = (ImageButton) findViewById(R.id.AddProjectB);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        ProjectDetails = (ImageButton) findViewById(R.id.projectDetails);



        searchEditText = findViewById(R.id.searchEditText);
        dataTextView = findViewById(R.id.projectsDataView);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchProject(searchEditText.getText().toString());
            }
        });
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

    private void searchProject(String query) {
        try {
            FileInputStream fis = openFileInput("project_data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] projectDetails = line.split(",");
                if (projectDetails[0].equalsIgnoreCase(query) || projectDetails[1].equalsIgnoreCase(query)) {
                    // Match found
                    found = true;
                    showSearchResult(formatData(line));
                    break;
                }
            }
            if (!found) {
                showSearchResult("No project found with given name or subject");
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred during search", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSearchResult(String message) {
        String[] lines = message.split("\n");
        String projectName = "";
        for (String line : lines) {
            if (line.startsWith("Project Name")) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    projectName = parts[1].trim();
                }
                break;
            }
        }
        String finalProjectName = projectName;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search Result");
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProject(finalProjectName);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteProject(String projectNameToDelete) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search Result");
        String message = "Are you sure that you want to delete  "+projectNameToDelete+"?";
        builder.setMessage(message);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //ConfirmDeleteProject(projectNameToDelete);
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ConfirmDeleteProject(projectNameToDelete);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void ConfirmDeleteProject(String projectNameToDelete) {
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


