package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity11 extends AppCompatActivity {
    TextView tv111;
    ListView lv111;
    private DatabaseReference qDatabase;
    public String uid_make;
    public static final String UID_F = "as";
    public static final String EVENT_NAMEID = "asa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        lv111 = findViewById(R.id.lv111);
        tv111 = findViewById(R.id.tv111);
        ArrayList<String> textArray = new ArrayList<String>();
        Intent ac111 = getIntent();
        uid_make = ac111.getStringExtra(MainActivity8.UID_C);
        qDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        qDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MainActivity11.this, "Loading...", Toast.LENGTH_SHORT).show();
                for(DataSnapshot ss : snapshot.child(uid_make).child("My_Created_Events").getChildren()){

                    String event_name = ss.getKey().toString();
                    String event_id = ss.child("Eventid").getValue().toString();
                    textArray.add("\nEvent Name :- "+event_name + "\n" +"Event ID :- "+ event_id+"\n");
                }
                ArrayAdapter mainAdapter = new ArrayAdapter(MainActivity11.this, android.R.layout.simple_list_item_1,textArray);
                lv111.setAdapter(mainAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lv111.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity11.this, "Opening this event !", Toast.LENGTH_SHORT).show();
                Object obj = lv111.getAdapter().getItem(i);
                String l_enameid = obj.toString();
                Intent ac112 = new Intent(MainActivity11.this,MainActivity12.class);
                ac112.putExtra(EVENT_NAMEID,l_enameid);
                ac112.putExtra(UID_F,uid_make);
                startActivity(ac112);
            }
        });
    }
}