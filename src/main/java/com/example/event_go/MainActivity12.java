package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity12 extends AppCompatActivity {
    TextView tv121,tv122,tv123,tv124,tv125,tv126,tv127;
    Button b121,b123,b122;
    DatabaseReference eDatabase;
    public String uid_ac12;
    public static final String UIDAC12 = "Asa";
    public static final String EVAC12 = "saa";
    public String ev_name_ac12;
    public String naid_ac12;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        tv121 = findViewById(R.id.tv121);
        tv122 = findViewById(R.id.tv122);
        tv123 = findViewById(R.id.tv123);
        tv124 = findViewById(R.id.tv124);
        tv125 = findViewById(R.id.tv125);
        tv126 = findViewById(R.id.tv126);
        tv127 = findViewById(R.id.tv127);
        b121 = findViewById(R.id.b121);
        b122 = findViewById(R.id.b122);
        b123 = findViewById(R.id.b123);
        Intent ac121 = getIntent();
        uid_ac12 = ac121.getStringExtra(MainActivity11.UID_F);
        naid_ac12 = ac121.getStringExtra(MainActivity11.EVENT_NAMEID);
        eDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        eDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int ans = 0;
                   for(DataSnapshot a : snapshot.child(uid_ac12).child("My_Created_Events").getChildren()){
                       String ac12_evna = a.getKey().toString();
                       String ac12_evid = a.child("Eventid").getValue().toString();
                       String ac12_fin = "\nEvent Name :- "+ac12_evna + "\n" +"Event ID :- "+ ac12_evid+"\n";
                       if(ac12_fin.equals(naid_ac12)){
                           ans = 1;
                           ev_name_ac12 = ac12_evna;
                           String ac12_evcon = a.child("Eventcontact").getValue().toString();
                           String ac12_evdate = a.child("Eventdate").getValue().toString();
                           String ac12_evtime = a.child("Eventtime").getValue().toString();
                           String ac12_evorg = a.child("Eventorg").getValue().toString();
                           String ac12_evpass = a.child("Eventpasses").getValue().toString();
                           String ac12_evvenue = a.child("Eventvenue").getValue().toString();
                           tv122.setText("Given Contact :- "+ac12_evcon);
                           tv123.setText("Event Date :- "+ac12_evdate);
                           tv124.setText("Event Time :- "+ac12_evtime);
                           tv125.setText("Organizer :- "+ac12_evorg);
                           tv126.setText("Available Passes :- " + ac12_evpass);
                           tv127.setText("Event Venue :- "+ac12_evvenue);
                           break;
                       }
                   }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
         b123.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 eDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                 eDatabase.child(uid_ac12).child("My_Created_Events").child(ev_name_ac12).child("Eventpasses").setValue("0");
                 Toast.makeText(MainActivity12.this, "Bookings Closed Successfully !", Toast.LENGTH_SHORT).show();
                 Intent ac123 = new Intent(MainActivity12.this,MainActivity8.class);
                 startActivity(ac123);
             }
         });
         b122.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 eDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                 eDatabase.child(uid_ac12).child("My_Created_Events").child(ev_name_ac12).child("Eventpasses").setValue("100");
                 Toast.makeText(MainActivity12.this, "Bookings will be only opened temporarily for maximum 100 customers !", Toast.LENGTH_SHORT).show();
                 Intent ac123 = new Intent(MainActivity12.this,MainActivity8.class);
                 startActivity(ac123);
             }
         });
         b121.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(MainActivity12.this, "Opening Booking List .", Toast.LENGTH_SHORT).show();
                 Intent ac125 = new Intent(MainActivity12.this,MainActivity2.class);
                 ac125.putExtra(UIDAC12,uid_ac12);
                 ac125.putExtra(EVAC12,ev_name_ac12);
                 startActivity(ac125);
             }
         });
    }
}