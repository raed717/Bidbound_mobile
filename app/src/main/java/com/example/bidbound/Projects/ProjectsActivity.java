package com.example.bidbound.Projects;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bidbound.Projects.NewProject;
import com.example.bidbound.R;
import com.example.bidbound.adapters.ProjectAdapter;
import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.entities.Project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends AppCompatActivity implements ProjectAdapter.ProjectAdapterListener {

    private ImageButton AddProjectButton, searchButton, deleteButton;
    private EditText searchEditText;

    private RecyclerView projectsRecyclerView;
    private ProjectAdapter projectAdapter;
    private List<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        AddProjectButton = (ImageButton) findViewById(R.id.AddProjectB);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        searchButton = (ImageButton) findViewById(R.id.searchButton);

        searchEditText = findViewById(R.id.searchEditText);

        projectsRecyclerView = findViewById(R.id.projectsDataView);
        projectList = new ArrayList<>(); // Replace with actual data retrieval
        projectAdapter = new ProjectAdapter(this, projectList, this);
        projectsRecyclerView.setAdapter(projectAdapter);
        loadProjects();
        projectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchProject(searchEditText.getText().toString());
            }
        });

        AddProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddProject();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProject(searchEditText.getText().toString());
            }
        });



    }

    @Override
    public void onEditProject(Project project) {
        Intent intent = new Intent(this, ProjectDetails.class);
        intent.putExtra("projectId", project.getId());
        startActivity(intent);
    }


    public void editProject(Project project){
        Intent intent = new Intent(this, com.example.bidbound.Projects.ProjectDetails.class);
        intent.putExtra("projectId", project.getId()); // Pass the project ID or the whole object if it's Parcelable or Serializable
        startActivity(intent);
    }


    public void openAddProject(){
        Intent intent = new Intent(this, NewProject.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteProject(final String query){
        new AsyncTask<Void, Void, List<Project>>() {
            @Override
            protected List<Project> doInBackground(Void... voids) {
                // Adjust the query to search for a partial match and not case-sensitive
                String sqlQuery = "%" + query + "%";
                List<Project> projectsToDelete = null;
                projectsToDelete = AppDatabase.getAppDatabase(getApplicationContext())
                        .projectDAO().searchProjects(sqlQuery);
                if (!projectsToDelete.isEmpty()) {
                    for (Project project : projectsToDelete) {
                        AppDatabase.getAppDatabase(getApplicationContext())
                                .projectDAO().deleteProject(project);
                    }
                }
                return projectsToDelete; // Return the list of projects
            }

            @Override
            protected void onPostExecute(List<Project> projects) {
                if (projects != null && !projects.isEmpty()) {
                    showToast("Project deleted successfully");
                } else {
                    showToast("No matching projects found");
                }
                projectList.clear();
                projectList.addAll(projects);
                projectAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("StaticFieldLeak")
    private void searchProject(final String query) {
        new AsyncTask<Void, Void, List<Project>>() {
            @Override
            protected List<Project> doInBackground(Void... voids) {
                // Adjust the query to search for a partial match and not case-sensitive
                String sqlQuery = "%" + query + "%";
                return AppDatabase.getAppDatabase(getApplicationContext()).projectDAO().searchProjects(sqlQuery);
            }

            @Override
            protected void onPostExecute(List<Project> projects) {
                projectList.clear();
                projectList.addAll(projects);
                projectAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


    @SuppressLint("StaticFieldLeak")
    private void loadProjects() {
        new AsyncTask<Void, Void, List<Project>>() {
            @Override
            protected List<Project> doInBackground(Void... voids) {
                // Retrieve projects from the database
                return AppDatabase.getAppDatabase(getApplicationContext()).projectDAO().getAllProjects();
            }

            @Override
            protected void onPostExecute(List<Project> projects) {
                projectList.clear();
                projectList.addAll(projects);
                projectAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}


