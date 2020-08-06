package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
Button singnUp;Button go;
    TextInputLayout regEmail, regPassword;

    DatabaseReference databaseReference;
    FirebaseAuth mfirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mfirebaseAuth=FirebaseAuth.getInstance();

        regEmail = findViewById(R.id.email);

        regPassword = findViewById(R.id.password);
        go=findViewById(R.id.login_btn);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email=regEmail.getEditText().getText().toString();

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
                    regPassword.setError("Password must be >=6 character");
                    return;
                }

                mfirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Succesful Login.",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),Home.class));
                            Intent intent= new Intent(MainActivity.this,Home.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        singnUp=findViewById(R.id.signup_btn);
        singnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,singUp.class);
                startActivity(intent);
    }
});

    }
}