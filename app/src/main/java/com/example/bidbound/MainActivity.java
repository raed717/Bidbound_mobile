package com.example.bidbound;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView signup;
SharedPreferences myPref ;
public static final String Pref = "MyPref";
EditText login , password ;
Button save ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        signup=findViewById(R.id.SignUpTextView);
        signup.setOnClickListener(e -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }





}