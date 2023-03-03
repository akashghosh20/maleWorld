package com.example.maleworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    EditText email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         getSupportActionBar().hide();
         auth = FirebaseAuth.getInstance();
         email = findViewById(R.id.email);
         password = findViewById(R.id.password);

    }

    public void signUp(View view) {
        Intent intent = new Intent(loginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Give your mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<6){
            Toast.makeText(this, "Password must be 6 number", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(loginActivity.this, "Successfully loged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(loginActivity.this, "Log in failed "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}