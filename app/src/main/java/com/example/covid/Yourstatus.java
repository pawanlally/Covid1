package com.example.covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Yourstatus extends AppCompatActivity {

    FirebaseDatabase root;
    DatabaseReference reference;
    //FirebaseFirestore fstore;
    FirebaseAuth fauth;
    TextInputLayout uname,uage,pin;
    CheckBox uyfever,uycough,unfever,uncough;
    Button go;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourstatus);

        uname=findViewById(R.id.name);
        uage=findViewById(R.id.age);
        uyfever=findViewById(R.id.yFever);
        unfever=findViewById(R.id.nFever);
        uycough=findViewById(R.id.yCough);
        uncough=findViewById(R.id.nCough);
        go=findViewById(R.id.check);
        pin=findViewById(R.id.pincode);


       /* Spinner mySpinner = (Spinner) findViewById(R.id.prof);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Yourstatus.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.professions));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);*/
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nname = uname.getEditText().getText().toString();
                String nage = uage.getEditText().getText().toString();
                String npin=pin.getEditText().getText().toString();
                String nyfever=uyfever.getText().toString();
                String nnfever=unfever.getText().toString();
                String nycough=uycough.getText().toString();
                String nncough=uncough.getText().toString();

                String nfever,ncough;
                if(uyfever.isChecked()){
                    nfever=nyfever;
                }
                else {
                    nfever=nnfever;
                }
                if(uycough.isChecked()){
                    ncough=nycough;
                }
                else {
                    ncough=nncough;
                }
                //userId=fauth.getCurrentUser().getUid();

                //DocumentReference documentReference=fstore.collection("status").document(userId);
                root=FirebaseDatabase.getInstance();
                reference=root.getReference("status");
                UserHelperClass helperClass=new UserHelperClass(nname,nage,npin,nfever,ncough);
                reference.child(npin).setValue(helperClass);

                if(uyfever.isChecked()&&uycough.isChecked()){
                    Toast.makeText(Yourstatus.this, "You are Sick and needs test", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Yourstatus.this, Home.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Yourstatus.this, "You are Safe.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Yourstatus.this, Home.class);
                    startActivity(intent);
                }

            }
        });



    }
}