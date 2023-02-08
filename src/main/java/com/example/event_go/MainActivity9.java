package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity9 extends AppCompatActivity {
    EditText ed91,ed92,ed93,ed94,ed95,ed96,ed97,ed98;
    Button b91;
    public String this_uid;

    private DatabaseReference mDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        ed91 = findViewById(R.id.ed91);
        ed92 = findViewById(R.id.ed92);
        ed93 = findViewById(R.id.ed93);
        ed94 = findViewById(R.id.ed94);
        ed95 = findViewById(R.id.ed95);
        ed96 = findViewById(R.id.ed96);
        ed97 = findViewById(R.id.ed97);
        ed98 = findViewById(R.id.ed98);
        b91 = findViewById(R.id.b91);
        Intent ac91 = getIntent();
        this_uid = ac91.getStringExtra(MainActivity8.UID_C);
        b91.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ename = ed91.getText().toString();
                String eob = ed92.getText().toString();
                String econtact = ed93.getText().toString();
                String eid = ed94.getText().toString();
                String edate = ed95.getText().toString();
                String etime = ed96.getText().toString();
                String evenue = ed97.getText().toString();
                String epasses = ed98.getText().toString();
                if(ename.isEmpty()||eob.isEmpty()||econtact.isEmpty()||eid.isEmpty()||edate.isEmpty()||etime.isEmpty()||evenue.isEmpty()||epasses.isEmpty()){
                    Toast.makeText(MainActivity9.this, "Enter all the required fields and try again !", Toast.LENGTH_SHORT).show();
                }
                else {
                    mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int ans = 0;
                            for(DataSnapshot sp : snapshot.getChildren()){
                                for(DataSnapshot ss : sp.child("My_Created_Events").getChildren()){
                                       String input_eid = ss.child("Eventid").getValue().toString();
                                       String input_ename = ss.getKey().toString();
                                       if(input_eid.equals(eid)|| input_ename.equals(ename)){
                                           ans = 1;
                                           Toast.makeText(MainActivity9.this, "Choose a unique Event Id and Event Name, this event Id or event Name  already exists !", Toast.LENGTH_SHORT).show();
                                           break;
                                       }
                                 }
                                if(ans==1){
                                    break;
                                }
                            }
                            if(ans==0){

                                mDatabase.child(this_uid).child("My_Created_Events").child(ename).child("Eventorg").setValue(eob);
                                mDatabase.child(this_uid).child("My_Created_Events").child(ename).child("Eventcontact").setValue(econtact);
                                mDatabase.child(this_uid).child("My_Created_Events").child(ename).child("Eventid").setValue(eid);
                                mDatabase.child(this_uid).child("My_Created_Events").child(ename).child("Eventdate").setValue(edate);
                                mDatabase.child(this_uid).child("My_Created_Events").child(ename).child("Eventtime").setValue(etime);
                                mDatabase.child(this_uid).child("My_Created_Events").child(ename).child("Eventvenue").setValue(evenue);
                                mDatabase.child(this_uid).child("My_Created_Events").child(ename).child("Eventpasses").setValue(epasses);
                                Toast.makeText(MainActivity9.this, "Details Saved Successfully", Toast.LENGTH_SHORT).show();
                                Intent ac91 = new Intent(MainActivity9.this,MainActivity8.class);
                                startActivity(ac91);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }
}