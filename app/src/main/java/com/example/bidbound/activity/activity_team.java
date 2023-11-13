package com.example.bidbound.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidbound.R;

public class activity_team extends AppCompatActivity {
    private Button allteamsButton;
    private Button addteambutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        allteamsButton = (Button) findViewById(R.id.allteams_button);
        addteambutton = (Button) findViewById(R.id.newteam_button);
        addteambutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdd();
            }
        });
        allteamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllTeams();
            }
        });
    }

    public void openAdd() {
        startActivity(new Intent(this, addTeam.class));
    }
    public void openAllTeams() {
        startActivity(new Intent(this, AllTeamsActivity.class));
    }
}