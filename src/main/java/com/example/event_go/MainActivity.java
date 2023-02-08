package com.example.event_go;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
     public Button button11;
     ImageView iv1;
     FirebaseAuth mauth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button11 = findViewById(R.id.button11);
        iv1 = findViewById(R.id.iv1);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauth  = FirebaseAuth.getInstance();
                FirebaseUser myuser = mauth.getCurrentUser();
                if(myuser != null){
                   Intent ac12 = new Intent(MainActivity.this,MainActivity8.class);
                   startActivity(ac12);
                }
                else{
                    Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                    startActivity(intent);
                }

            }
        });
    }
}