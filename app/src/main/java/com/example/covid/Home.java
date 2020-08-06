package com.example.covid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    ImageView yourStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        yourStatus= findViewById(R.id.status);
        yourStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Yourstatus.class);
                startActivity(intent);
            }
        });
    }

    public void updates(View view) {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.worldometers.info/coronavirus/"));
        startActivity(browserIntent);

    }

    public void media(View view) {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/events-as-they-happen"));
        startActivity(browserIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.commonmenus,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.logout){
            startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);


    }
}