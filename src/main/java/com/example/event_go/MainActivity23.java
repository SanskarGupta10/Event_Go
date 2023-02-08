package com.example.event_go;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity23 extends AppCompatActivity {
    TextView tv231;
    Button b231;
    public String uidac23;
    public String cuidac23;
    public String evnaac23;
    DatabaseReference nDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);
        tv231 = findViewById(R.id.tv231);
        b231 = findViewById(R.id.b231);
        Intent ac231 = getIntent();
        uidac23 = ac231.getStringExtra(MainActivity22.MUIDAC22);
        cuidac23 = ac231.getStringExtra(MainActivity22.CUSTIDAC22);
        evnaac23 = ac231.getStringExtra(MainActivity22.MEVNAC22);
        b231.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                nDatabase.child(uidac23).child("My_Created_Events").child(evnaac23).child("Customers").child(cuidac23).setValue("2");
                Toast.makeText(MainActivity23.this, "Payment Status marked successfully !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}