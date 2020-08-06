package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class singUp extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    DatabaseReference databaseReference;
    FirebaseAuth mfirebaseAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sing_up);

        mfirebaseAuth=FirebaseAuth.getInstance();
        regName = findViewById(R.id.name);
        //regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phoneNo);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

       /* if(mfirebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/
       regToLoginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(singUp.this,MainActivity.class);
               startActivity(intent);
           }
       });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=regName.getEditText().getText().toString();
                //String username=regUsername.getText().toString();
                String email=regEmail.getEditText().getText().toString();
                String phoneNo=regPhoneNo.getEditText().getText().toString();
                String password=regPassword.getEditText().getText().toString();
                if(TextUtils.isEmpty(email)){
                    regEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    regPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    regPassword.setError("Passwoed must be >=6 character");
                    return;
                }


                mfirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(singUp.this,"User Created.",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),Home.class));
                            Intent intent=new Intent(singUp.this,Home.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(singUp.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}