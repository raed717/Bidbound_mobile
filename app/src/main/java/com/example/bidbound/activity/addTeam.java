package com.example.bidbound.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.bidbound.SignUpActivity;
import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.R;
import com.example.bidbound.entities.Team;

import java.util.ArrayList;
import java.util.List;

public class addTeam extends AppCompatActivity {
    private Button add;
    private EditText name, message,expertise;
    private Context context;
    AppDatabase database;
    private List<Team> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        context = this;
        add = findViewById(R.id.addteamBtn);
        expertise = findViewById(R.id.teamExp);
        name = findViewById(R.id.teamName);
        message = findViewById(R.id.teamMessage);
        database = AppDatabase.getAppDatabase(context);
        teamList = new ArrayList<>();
        // Spinner teamStatusSpinner = findViewById(R.id.teamStatusSpinner);
        //  String status = teamStatusSpinner.getSelectedItem().toString();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                         if (item.getItemId() == R.id.bottom_dec) {
                            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                            overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_left);
                            finish();
                            return true;

                          }
                        return true;
                    }
                });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();

                Team newTeam = new Team();
                newTeam.setTeamName(name.getText().toString());
                newTeam.setTeamMessage(message.getText().toString());
                newTeam.setExpertise(expertise.getText().toString());
                // newTeam.setTeamStatus(status);
                database.teamDAO().insertOne(newTeam);
                listUpdate();
                for(Team team : teamList){

                    stringBuilder.append(team.getTeamName()+"  "+team.getExpertise()+"  "+team.getTeamStatus() );
                    stringBuilder.append("\n");
                }
                String allData = stringBuilder.toString();
                showToast(allData);
            }



        });



    }
    private void listUpdate(){
        teamList.clear();
        teamList.addAll(database.teamDAO().getAll());
    }
    private void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}