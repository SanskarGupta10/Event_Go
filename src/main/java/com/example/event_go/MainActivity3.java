package com.example.event_go;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {
     Button b31,b32;
     ImageView iv31,iv32;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b31 = findViewById(R.id.b31);
        b32 = findViewById(R.id.b32);
        iv31 = findViewById(R.id.iv31);
        iv32 = findViewById(R.id.iv32);
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent31 = new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(intent31);
            }
        });
        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent32 = new Intent(MainActivity3.this,MainActivity5.class);
                startActivity(intent32);
            }
        });
    }
}