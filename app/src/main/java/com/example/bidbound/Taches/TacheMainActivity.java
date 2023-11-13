package com.example.bidbound.Taches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bidbound.MainActivity;
import com.example.bidbound.Taches.Fragments.AfficherListTaches;
import com.example.bidbound.Taches.Fragments.AjouterTache;

import com.example.bidbound.R;

public class TacheMainActivity extends AppCompatActivity {
    private Button allTasksButton;
    private Button HomeButton;
    private Button NewTaskButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tache_activity_main);
        allTasksButton = findViewById(R.id.alltasks_button) ;
        HomeButton = findViewById(R.id.home_button_Task) ;
        NewTaskButton = findViewById(R.id.newtask_button);

        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfficherListActivity();
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });

        NewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewTask();
            }
        });
    }

    public void AfficherListActivity(){
        Intent intent = new Intent(this, AfficherListTaches.class);
        startActivity(intent);
    }
    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openNewTask(){
        Intent intent = new Intent(this, AjouterTache.class);
        startActivity(intent);
    }
}
