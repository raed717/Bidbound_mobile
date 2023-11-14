package com.example.bidbound.Projects;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.entities.Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewProject extends AppCompatActivity {
    private String selectedDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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


        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        addNewProject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewProject();
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

    private void addNewProject() {
        String projectName = projectNameEditText.getText().toString();
        String projectSubject = projectSubjectEditText.getText().toString();
        Date startDate = null;
        try {
            startDate = dateFormat.parse(selectedDate);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Please select a valid date", Toast.LENGTH_SHORT).show();
            return;
        }

        final Project newProject = new Project(projectName, projectSubject, startDate);
        new InsertProjectTask(this).execute(newProject);

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

    private static class InsertProjectTask extends AsyncTask<Project, Void, Void> {
        private WeakReference<Context> contextReference;

        InsertProjectTask(Context context) {
            contextReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Project... projects) {
            Context context = contextReference.get();
            if (context != null) {
                AppDatabase db = AppDatabase.getAppDatabase(context);
                db.projectDAO().insertProject(projects[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Context context = contextReference.get();
            if (context != null) {
                Toast.makeText(context, "Project added", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
