package com.example.bidbound.Taches.Fragments;



import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.View;

import android.widget.Toast;

import com.example.bidbound.R;



public class AfficherListTachesFragment extends AppCompatActivity {
    private ImageButton taskDetails,AddTaskB, searchButtonTasks;
    private TextView tasksDataView;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_afficher_list_taches);
        AddTaskB = (ImageButton) findViewById(R.id.AddTaskB);
        searchButtonTasks = (ImageButton) findViewById(R.id.searchButtonTasks);
        taskDetails = (ImageButton) findViewById(R.id.taskDetails);
        searchEditText = findViewById(R.id.searchEditText);
        tasksDataView = findViewById(R.id.tasksDataView);


        searchButtonTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMember(searchEditText.getText().toString());
            }
        });
        taskDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentaskDetails();
            }
        });
        AddTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddMember();
            }
        });

        String data = readDataFromFile();
        String formattedData = formatData(data);
        if (data != null) {
            // Set the data to the TextView
            tasksDataView.setText(formattedData);
        } else {
            // Handle the case where there is no data
            tasksDataView.setText("No data found");
        }

    }

    public void opentaskDetails(){
        Intent intent = new Intent(this, com.example.bidbound.Taches.Fragments.DetailsTacheFragment.class);
        startActivity(intent);
    }
    public void openAddMember(){
        Intent intent = new Intent(this, AjouterTache.class);
        startActivity(intent);
    }
    private String readDataFromFile() {
        try {
            FileInputStream fis = openFileInput("Task_data.txt");
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
        String[] members = rawData.split("\n");

        for (String member : members) {
            String[] taskDetails = member.split(",");
            if (taskDetails.length >= 3) { // Ensure there are enough fields
                String taskName = taskDetails[0];
                String Description = taskDetails[1];
                String Complexite = taskDetails[2];

                formattedData.append("task  Name: ").append(taskName).append("\n");
                formattedData.append("Description : ").append(Description).append("\n");
                formattedData.append("Complexite : ").append(Complexite).append("\n\n");
            }
        }
        return formattedData.toString();
    }

    private void searchMember(String query) {
        try {
            FileInputStream fis = openFileInput("member_data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] memberDetails = line.split(",");
                if (memberDetails[0].equalsIgnoreCase(query) || memberDetails[1].equalsIgnoreCase(query)) {
                    // Match found
                    found = true;
                    showSearchResult(formatData(line));
                    break;
                }
            }
            if (!found) {
                showNoSearchResult(query);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred during search", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNoSearchResult(String query) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search Result");
        builder.setMessage("No Results Found for: "+"'"+query+"'");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSearchResult(String message) {
        String[] lines = message.split("\n");
        String memberFirstName = "";
        for (String line : lines) {
            if (line.startsWith("Member FirstName")) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    memberFirstName = parts[1].trim();
                }
                break;
            }
        }
        String finalmemberFirstName = memberFirstName;
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
                deleteMember(finalmemberFirstName);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteMember(String MemberNameToDelete) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search Result");
        String message = "Are you sure that you want to delete  "+MemberNameToDelete+"?";
        builder.setMessage(message);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //ConfirmDeleteProject(projectNameToDelete);
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ConfirmDeleteMember(MemberNameToDelete);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void ConfirmDeleteMember(String memberNameToDelete) {
        try {
            FileInputStream fis = openFileInput("member_data.txt");
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            fis.close();

            if (bytesRead > 0) {
                String existingData = new String(buffer, 0, bytesRead);

                String[] members = existingData.split("\n");
                StringBuilder updatedData = new StringBuilder();

                boolean memberDeleted = false;

                for (String member : members) {
                    String[] memberDetails = member.split(",");
                    if (memberDetails.length >= 1 && memberDetails[0] != null
                            && !memberDetails[0].equals(memberNameToDelete)) {
                        updatedData.append(member).append("\n");
                    } else {
                        // Project found and deleted
                        memberDeleted = true;
                    }
                }

                if (!memberDeleted) {
                    Toast.makeText(this, "member not found", Toast.LENGTH_SHORT).show();
                } else {
                    FileOutputStream fos = openFileOutput("member_data.txt", Context.MODE_PRIVATE);
                    fos.write(updatedData.toString().getBytes());
                    fos.close();

                    Toast.makeText(this, "member deleted successfully", Toast.LENGTH_SHORT).show();

                    // Update your UI components if needed
                    String updatedDataString = readDataFromFile();
                  //  tasksDataView.setText(formatData(updatedDataString));
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