package com.example.bidbound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.example.bidbound.Projects.ProjectMenuActivity;
import com.example.bidbound.Membres.MemberActivity;
import com.example.bidbound.Taches.TacheMainActivity;

public class MainActivity extends AppCompatActivity {
    private Button TaskButton;
    private Button ProjectButton;
    private Button MemberButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TaskButton = (Button) findViewById(R.id.Tasks);
        ProjectButton = (Button) findViewById(R.id.Projects);
        MemberButton = (Button) findViewById(R.id.Member);
        TaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTasks();
            }
        });
        ProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProjects();
            }
        });
        MemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMember();
            }
        });
    }

    public void openTasks(){
        Intent intent = new Intent(this, TacheMainActivity.class);
        startActivity(intent);
    }
    public void openMember(){
        Intent intent = new Intent(this, MemberActivity.class);
        startActivity(intent);
    }

    public void openProjects(){
//        Intent intent = new Intent(this, ProjectMenuActivity.class);
//        startActivity(intent);
    }
}