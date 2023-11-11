package com.example.bidbound;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView signup;
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


    public void openSignUpActivity(View view) {
    }


}