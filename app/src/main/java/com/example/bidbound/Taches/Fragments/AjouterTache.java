package com.example.bidbound.Taches.Fragments ;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;



import com.example.bidbound.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class AjouterTache extends AppCompatActivity {

    private Button allTasks_button, addNewTask_button, Clear_button;
    private EditText Complexite , TaskDescription_input, taskName_input;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ajouter_tache);
        allTasks_button = findViewById(R.id.allTasks_button) ;
        addNewTask_button = findViewById(R.id.addNewTask_button) ;
        Clear_button = findViewById(R.id.Clear_button) ;
        Complexite = findViewById(R.id.Complexite) ;
        TaskDescription_input = findViewById(R.id.TaskDescription_input) ;
        taskName_input = findViewById(R.id.taskName_input) ;

        addNewTask_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve data from the input fields
                String taskName = taskName_input.getText().toString();
                String taskDescription = TaskDescription_input.getText().toString();
                String complexitee = Complexite.getText().toString();
                Boolean formCheck = true;

                if(formCheck){
                    Log.d("AjouterTache", "Task Name: " + taskName);
                    Log.d("AjouterTache", "Task Subject: " + taskDescription);
                    Log.d("AjouterTache", "Complexite: " + complexitee);
                    Toast.makeText(AjouterTache.this, "New task added successfully", Toast.LENGTH_SHORT).show();
                    saveDataToFile(taskName, complexitee, taskDescription);
                    logDataFromFile();
                }
            }
        });
        allTasks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTasksActivity();
            }
        });
        Clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskName_input.setText("");
                Complexite.setText("");
                TaskDescription_input.setText("");
            }
        });

    }
    public void openTasksActivity(){
        Intent intent = new Intent(this, AfficherListTachesFragment.class);
        startActivity(intent);
    }

    private void saveDataToFile(String taskName, String complexite, String TaskDescription) {
        try {
            String data = taskName + "," + complexite + "," + TaskDescription + "\n";
            FileOutputStream fos = openFileOutput("Task_data.txt", Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logDataFromFile() {
        try {
            FileInputStream fis = openFileInput("task_data.txt");
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            fis.close();
            if (bytesRead > 0) {
                String fileContent = new String(buffer, 0, bytesRead);
                Log.d("NewTask", "Content of task_data.txt: " + fileContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
