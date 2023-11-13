package com.example.bidbound.Membres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bidbound.MainActivity;
import com.example.bidbound.Membres.NewMember;
import com.example.bidbound.Membres.MemberActivity;
import com.example.bidbound.R;

public class MemberMenuActivity extends AppCompatActivity {

    private Button allmembersButton;
    private Button HomeButton;
    private Button NewMemberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_menu);
        allmembersButton = (Button) findViewById(R.id.allmembers_button);
        HomeButton = (Button) findViewById(R.id.home_button);
        NewMemberButton = (Button) findViewById(R.id.newmember_button);
        allmembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMembersActivity();
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });

        NewMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewMember();
            }
        });
    }
    public void openMembersActivity(){
        Intent intent = new Intent(this, MemberActivity.class);
        startActivity(intent);
    }
    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openNewMember(){
        Intent intent = new Intent(this, NewMember.class);
        startActivity(intent);
    }
}