package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupAct extends AppCompatActivity {

    TextView textview;
    EditText name,id,password,mobile;
    Button submit;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textview = findViewById(R.id.textView);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        mobile = findViewById(R.id.mobile);
        submit = findViewById(R.id.submit);
        fauth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuser();
            }
        });
    }

    private void signupuser(){
        String email = id.getText().toString();
        String passw = password.getText().toString();

        // check email and password authentication

        if(TextUtils.isEmpty(email)){
            id.setError("Email id is Required");
            return;
        }
        if(TextUtils.isEmpty(passw)){
            password.setError("password is Required");
            return;
        }
        if(passw.length()<6){
            password.setError("password must be 6 character");
            return;
        }

        fauth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(SignupAct.this, MainActivity.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Registration failed!!"+" Please try again later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}