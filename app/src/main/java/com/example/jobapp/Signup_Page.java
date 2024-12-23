package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Page extends AppCompatActivity {
    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        email=findViewById(R.id.editText6);
        password=findViewById(R.id.editText5);
        Button register = findViewById(R.id.button2);
        TextView text = findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();
                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Signup_Page.this, "Complete the credentials", Toast.LENGTH_SHORT).show();
                }
                else
                if(txt_password.length()<6)
                {
                    Toast.makeText(Signup_Page.this, "Password is short!", Toast.LENGTH_SHORT).show();
                }
                else
                    registerUser(txt_email,txt_password);


            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Signup_Page.this,Login_Page.class);
                startActivity(i);
            }
        });

    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Signup_Page.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Signup_Page.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup_Page.this,MainActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Signup_Page.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


    }}
