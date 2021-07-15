package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAct extends AppCompatActivity {

    EditText id,pass;
    Button login,signup;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.eid);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        fauth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginac();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupac();
            }
        });
    }

    private void loginac(){
        String email, password;
        email = id.getText().toString();
        password = pass.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),"Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }

        fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();

                    // if sign-in is successful
                    // intent to home activity
                    Intent intent = new Intent(LoginAct.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    // sign-in failed
                    Toast.makeText(getApplicationContext(),"Login failed!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void signupac(){
        Intent intent = new Intent(LoginAct.this, SignupAct.class);
        startActivity(intent);
    }
}