package com.example.bidbound.Projects;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bidbound.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class NewProject extends AppCompatActivity {
    private String selectedDate;

    private EditText projectNameEditText, projectSubjectEditText;
    private Button datePickerButton, addNewProject_button, Clear_button;
    private Button allprojectsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        projectNameEditText = findViewById(R.id.projectName_input);
        projectSubjectEditText = findViewById(R.id.ProjectSubject_input);
        datePickerButton = findViewById(R.id.DatePickerButton);
        addNewProject_button = findViewById(R.id.addNewProject_button);
        allprojectsButton = (Button) findViewById(R.id.allprojects_button);
        Clear_button = findViewById(R.id.Clear_button);

        //deleteAllData();

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        addNewProject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve data from the input fields
                String projectName = projectNameEditText.getText().toString();
                String projectSubject = projectSubjectEditText.getText().toString();
                Boolean formCheck = true;


                if (selectedDate == null || selectedDate.isEmpty() || projectNameEditText == null || projectSubjectEditText == null) {
                    Toast.makeText(NewProject.this, "Please fill the entire form", Toast.LENGTH_SHORT).show();
                    formCheck = true; // for test
                    return;
                }

                if(formCheck){
                    Log.d("NewProject", "Project Name: " + projectName);
                    Log.d("NewProject", "Project Subject: " + projectSubject);
                    Log.d("NewProject", "Selected Date: " + selectedDate);
                    Toast.makeText(NewProject.this, "New project added successfully", Toast.LENGTH_SHORT).show();
                    saveDataToFile(projectName, projectSubject, selectedDate);
                    logDataFromFile();
                }
            }
        });
        allprojectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProjectsActivity();
            }
        });
        Clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                projectNameEditText.setText("");
                projectSubjectEditText.setText("");
            }
        });
    }
    public void openProjectsActivity(){
        Intent intent = new Intent(this, ProjectsActivity.class);
        startActivity(intent);
    }
    private void showDatePickerDialog() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void saveDataToFile(String projectName, String projectSubject, String selectedDate) {
        try {
            String data = projectName + "," + projectSubject + "," + selectedDate + "\n";
            FileOutputStream fos = openFileOutput("project_data.txt", Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logDataFromFile() {
        try {
            FileInputStream fis = openFileInput("project_data.txt");
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            fis.close();
            if (bytesRead > 0) {
                String fileContent = new String(buffer, 0, bytesRead);
                Log.d("NewProject", "Content of project_data.txt: " + fileContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteAllData() {
        try {
            FileOutputStream fos = openFileOutput("project_data.txt", Context.MODE_PRIVATE);
            fos.close();
            Toast.makeText(this, "All data deleted successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
