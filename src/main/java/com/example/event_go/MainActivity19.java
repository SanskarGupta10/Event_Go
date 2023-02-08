package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity19 extends AppCompatActivity {
        public String uid_ac19;
        public static final String EVNAID = "sd";
        public static final String UIDAC19="sg";
        TextView tv191;
        ListView lv191;
        DatabaseReference mDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);
        tv191 = findViewById(R.id.tv191);
        lv191 = findViewById(R.id.lv191);
        Intent ac191 = getIntent();
        ArrayList<String> mEvents = new ArrayList<String>();
        uid_ac19 = ac191.getStringExtra(MainActivity8.UID_C);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot a : snapshot.child(uid_ac19).child("My_Booked_Events").getChildren()){
                    String ev_name = a.getKey().toString();
                    String ev_id = a.child("Eventid").getValue().toString();
                    mEvents.add("\nEvent Name :- "+ev_name + "\n" +"Event ID :- "+ ev_id+"\n");
                }
                ArrayAdapter mainAd = new ArrayAdapter(MainActivity19.this, android.R.layout.simple_list_item_1,mEvents);
                lv191.setAdapter(mainAd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    lv191.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity19.this, "Opening the Ticket !", Toast.LENGTH_SHORT).show();
            Object obj = lv191.getAdapter().getItem(i);
            String f_evnameid = obj.toString();
            Intent ac192 = new Intent(MainActivity19.this,MainActivity20.class);
            ac192.putExtra(EVNAID,f_evnameid);
            ac192.putExtra(UIDAC19,uid_ac19);
            startActivity(ac192);
        }
    });
    }
}