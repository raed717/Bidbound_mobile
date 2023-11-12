package com.example.bidbound;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.entities.user;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    SharedPreferences myPref ;
    public static final String Pref = "MyPref";
    EditText name , mail,  pass ;
    Button register ;

private AppDatabase base;
private List<user> user = new ArrayList<user>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name=findViewById(R.id.username);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        register=findViewById(R.id.signupbtn);
        myPref = getSharedPreferences(Pref,MODE_PRIVATE);
        SharedPreferences.Editor editor=myPref.edit();



        register.setOnClickListener(e -> {
            editor.putString("name", name.getText().toString());
            editor.putString("mail", mail.getText().toString());
            editor.putString("pwd", pass.getText().toString());
            editor.apply();




        });


    }

}
