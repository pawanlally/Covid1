package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class forgetPassword extends AppCompatActivity {

    Button pass;
    TextInputLayout regEmail;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        regEmail = findViewById(R.id.userEmail);
        pass = findViewById(R.id.userPass);

        firebaseAuth = FirebaseAuth.getInstance();

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= regEmail.getEditText().getText().toString();
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(forgetPassword.this,"Password send to your email", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(forgetPassword.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}