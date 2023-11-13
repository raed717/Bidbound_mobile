package com.example.bidbound;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bidbound.database.AppDatabase;
import com.example.bidbound.entities.user;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {
    EditText name , mail,  pass ;
    Button register ;

private AppDatabase instance;
private List<user> user = new ArrayList<user>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name=findViewById(R.id.username);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        register=findViewById(R.id.signupbtn);



        register.setOnClickListener(e -> {

            String userName = name.getText().toString();
            String userEmail = mail.getText().toString();
            String userPassword = pass.getText().toString();

            if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                // Log a message if any field is empty
                Log.d("SignUpActivity", "One or more fields are empty. User not added.");
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

            }
            else {
                AppDatabase.getAppDatabase(this).userDAO().addUser(new user(name.getText().toString(), mail.getText().toString(),pass.getText().toString()));
                Log.d("SignUpActivity", "User added: ");
                Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show();

            }




            });


    }

}
