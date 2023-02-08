package com.example.event_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity8 extends AppCompatActivity {
    TextView t81;
    Button b81,b82,b83,b84,b85;
    ImageView iv81;
    public String uidchild;
    public static final String UID_C="sd";
    private FirebaseAuth zauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        t81 = findViewById(R.id.t81);
        b81 = findViewById(R.id.b81);
        b82 = findViewById(R.id.b82);
        b83 = findViewById(R.id.b83);
        b84 = findViewById(R.id.b84);
        b85 = findViewById(R.id.b85);
        iv81 = findViewById(R.id.iv81);
        zauth = FirebaseAuth.getInstance();
        FirebaseUser myuser = zauth.getCurrentUser();
        if(myuser != null){
            uidchild = myuser.getUid();
        }
        else{
            Toast.makeText(this, "Something went wrong !", Toast.LENGTH_SHORT).show();
        }
        b82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac81 = new Intent(MainActivity8.this,MainActivity9.class);
                ac81.putExtra(UID_C,uidchild);
                startActivity(ac81);
            }
        });
        b81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac82 = new Intent(MainActivity8.this,MainActivity11.class);
                ac82.putExtra(UID_C,uidchild);
                startActivity(ac82);
            }
        });
        b83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac83 = new Intent(MainActivity8.this,MainActivity13.class);
                ac83.putExtra(UID_C,uidchild);
                startActivity(ac83);
            }
        });
        b85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac84 = new Intent(MainActivity8.this,MainActivity15.class);
                ac84.putExtra(UID_C,uidchild);
                startActivity(ac84);
            }
        });
        b84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ac86 = new Intent(MainActivity8.this,MainActivity19.class);
                ac86.putExtra(UID_C,uidchild);
                startActivity(ac86);
            }
        });
    }
}