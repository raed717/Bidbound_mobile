package com.example.bidbound.Membres;

import android.annotation.SuppressLint;
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

public class NewMember extends AppCompatActivity {
    private String selectedDate;

    private String FirstName;
    private String LastName;

    private EditText MemberFirstNameEditText, MemberLastNameEditText;
    private Button datePickerButton, addNewMember_button, Clear_button;
    private Button allmembersButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);
        MemberFirstNameEditText = findViewById(R.id.MemberFirstName_input);
        MemberLastNameEditText = findViewById(R.id.memberLastName_input);
        datePickerButton = findViewById(R.id.DatePickerButton);
        addNewMember_button = findViewById(R.id.addNewMember_button);
        allmembersButton = (Button) findViewById(R.id.allmembers_button);
        Clear_button = findViewById(R.id.Clear_button);

        //deleteAllData();

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        addNewMember_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve data from the input fields
                String FirstName =   MemberFirstNameEditText.getText().toString();
                String LastName =    MemberLastNameEditText.getText().toString();
                Boolean formCheck = true;


                if (selectedDate == null || selectedDate.isEmpty() || MemberFirstNameEditText == null || MemberLastNameEditText == null) {
                    Toast.makeText(NewMember.this, "Please fill the entire form", Toast.LENGTH_SHORT).show();
                    formCheck = true; // for test
                    return;
                }

                if(formCheck){
                    Log.d("NewMember", "Member First Name: " +  FirstName);
                    Log.d("NewMember", "Member Last Name: " + LastName);
                    Log.d("NewMember", "Selected Date: " + selectedDate);
                    Toast.makeText(NewMember.this, "New project added successfully", Toast.LENGTH_SHORT).show();
                    saveDataToFile( FirstName,LastName , selectedDate);
                    logDataFromFile();
                }
            }
        });
        allmembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMembersActivity();
            }
        });
        Clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          MemberFirstNameEditText.setText("");
                MemberLastNameEditText.setText("");
            }
        });
    }
    public void openMembersActivity(){
        Intent intent = new Intent(this, MemberActivity.class);
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

    private void saveDataToFile(String FirstName, String LastName, String selectedDate) {
        try {
            String data = FirstName + "," + LastName + "," + selectedDate + "\n";
            FileOutputStream fos = openFileOutput("member_data.txt", Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logDataFromFile() {
        try {
            FileInputStream fis = openFileInput("members_data.txt");
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            fis.close();
            if (bytesRead > 0) {
                String fileContent = new String(buffer, 0, bytesRead);
                Log.d("NewMember", "Content of members_data.txt: " + fileContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteAllData() {
        try {
            FileOutputStream fos = openFileOutput("members_data.txt", Context.MODE_PRIVATE);
            fos.close();
            Toast.makeText(this, "All data deleted successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
