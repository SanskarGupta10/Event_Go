package com.example.event_go;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity15 extends AppCompatActivity {
      Button b151,b152,b153,b154,b155;
      public String uid_ac15;
      FirebaseAuth mauth;
      public static final String UID_AC = "asa";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);
        b151 = findViewById(R.id.b151);
        b152 = findViewById(R.id.b152);
        b153 = findViewById(R.id.b153);
        b154 = findViewById(R.id.b154);
        b155 = findViewById(R.id.b155);
        Intent ac152 = getIntent();
        uid_ac15 = ac152.getStringExtra(MainActivity8.UID_C);
        b151.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac151 = new Intent(MainActivity15.this,MainActivity16.class);
                ac151.putExtra(UID_AC,uid_ac15);
                startActivity(ac151);
            }
        });
        b153.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mauth = FirebaseAuth.getInstance();
                FirebaseUser myuser = mauth.getCurrentUser();
                if(myuser != null){
                    mauth.signOut();
                    Toast.makeText(MainActivity15.this, "Logging Out !", Toast.LENGTH_SHORT).show();
                    Intent ac157 = new Intent(MainActivity15.this,MainActivity.class);
                    startActivity(ac157);
                }
            }
        });
        b154.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          Intent ac152 = new Intent(MainActivity15.this,MainActivity17.class);
          ac152.putExtra(UID_AC,uid_ac15);
          startActivity(ac152);
            }
        });
        b152.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent ac155 = new Intent(MainActivity15.this,MainActivity18.class);
           ac155.putExtra(UID_AC,uid_ac15);
           startActivity(ac155);
            }
        });
        b155.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac156 = new Intent(MainActivity15.this,MainActivity24.class);
                startActivity(ac156);
            }
        });
    }
}