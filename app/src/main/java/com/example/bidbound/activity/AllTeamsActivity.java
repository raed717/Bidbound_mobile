package com.example.bidbound.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.R;
import com.example.bidbound.activity.TeamAdapter;
import com.example.bidbound.entities.Team;
import com.example.bidbound.DAO.TeamDAO;

import java.util.ArrayList;
import java.util.List;

public class AllTeamsActivity extends AppCompatActivity {

    private RecyclerView teamsRecyclerView;
    private TeamAdapter teamAdapter;
    private TeamDAO teamDAO;
    private Context context;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allteams);
        context = this;
        teamsRecyclerView = findViewById(R.id.teamsRecyclerView);
        database = AppDatabase.getAppDatabase(context);
        // Replace YourRoomDatabase with your actual Room Database class
        teamDAO = database.teamDAO();

        // Set up RecyclerView
        teamAdapter = new TeamAdapter(new ArrayList<>()); // Initially, an empty list
        teamsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamsRecyclerView.setAdapter(teamAdapter);
        // Load teams from the database and update the RecyclerView
        List<Team> teams = teamDAO.getAll();
        teamAdapter.setTeams(teams);
    }
}
