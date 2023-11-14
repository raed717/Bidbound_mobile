package com.example.bidbound;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
 import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bidbound.activity.addTeam;
import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.entities.user;

public class MainActivity extends AppCompatActivity {
        TextView signup;
         EditText login , password ;
        Button save ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login = findViewById(R.id.email_l);
        password = findViewById(R.id.pwd);
        save = findViewById(R.id.login_btn);

        save.setOnClickListener(view -> {
            String userEmail = login.getText().toString();
            String userPassword = password.getText().toString();

             if (!userEmail.isEmpty() && !userPassword.isEmpty()) {
                 user signedInUser = AppDatabase.getAppDatabase(this).userDAO().signIn(userEmail, userPassword);

                if (signedInUser != null) {
                     Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

                 } else {
                     Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            } else {
                 Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            }
        });







        signup=findViewById(R.id.SignUpTextView);
        signup.setOnClickListener(e -> {
            Intent intent = new Intent(this, addTeam.class);
            startActivity(intent);
        });
    }





}