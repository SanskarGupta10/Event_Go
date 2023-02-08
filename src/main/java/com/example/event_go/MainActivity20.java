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

public class MainActivity20 extends AppCompatActivity {
    public String uid_ac20;
    public String ev_naid;

      TextView tv201,tv202,tv203,tv204,tv205,tv206,tv207;
      Button b201;
      DatabaseReference cDatabase;
      public String spas;
      DatabaseReference zDatabase;
      public String event_name;
      public String event_id;
      public int ef=0;
      DatabaseReference dDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);
        tv201 = findViewById(R.id.tv241);
        tv202 = findViewById(R.id.tv202);
        tv203 = findViewById(R.id.tv203);
        tv204 = findViewById(R.id.tv204);
        tv205= findViewById(R.id.tv205);
        tv206 = findViewById(R.id.tv206);
        tv207 = findViewById(R.id.tv207);
        b201 = findViewById(R.id.b201);
        Intent ac201 = getIntent();
        uid_ac20 = ac201.getStringExtra(MainActivity19.UIDAC19);
        ev_naid = ac201.getStringExtra(MainActivity19.EVNAID);
        cDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int ans = 0;
                Toast.makeText(MainActivity20.this, "Loading...", Toast.LENGTH_SHORT).show();
                 for(DataSnapshot a : snapshot.child(uid_ac20).child("My_Booked_Events").getChildren()){
                     String ev_na = a.getKey().toString();
                     String ev_id = a.child("Eventid").getValue().toString();
                     String ev_con = a.child("Eventcontact").getValue().toString();
                     String ev_date = a.child("Eventdate").getValue().toString();
                     String ev_time = a.child("Eventtime").getValue().toString();
                     String ev_venue = a.child("Eventvenue").getValue().toString();
                     String f_naid = "\nEvent Name :- "+ev_na + "\n" +"Event ID :- "+ ev_id+"\n";
                     if(f_naid.equals(ev_naid)){
                         ans = 1;
                         event_id = ev_id;
                         event_name = ev_na;
                          givePayStatus(uid_ac20,ev_na,ev_id);
                         tv203.setText("UID :- " + uid_ac20);
                         tv204.setText("Host Contact :- " + ev_con);
                         tv206.setText("Event Time :- "+ev_time);
                         tv205.setText("Event Date :- " + ev_date);
                         tv207.setText("Event Venue :- " + ev_venue);
                         break;
                     }
                 }
                 if(ans==0){
                     Toast.makeText(MainActivity20.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        b201.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
            dDatabase.child(uid_ac20).child("My_Booked_Events").child(event_name).removeValue();
            dDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int cns = 0;
                    int incpass;
                      for(DataSnapshot b : snapshot.getChildren()){
                          for(DataSnapshot c : b.child("My_Created_Events").getChildren()){
                              String t_inna = c.getKey().toString();
                              if(t_inna.equals(event_name)& ef==0){
                                  cns = 1;
                                  ef=1;
                                  String host_id = b.getKey().toString();
                                  String ev_passno = c.child("Eventpasses").getValue().toString();
                                  incpass = Integer.parseInt(ev_passno);
                                  incpass = incpass+1;
                                  String setPass = Integer.toString(incpass);
                                  spas = setPass;
                                  dDatabase.child(host_id).child("My_Created_Events").child(event_name).child("Eventpasses").setValue(spas);
                                  dDatabase.child(host_id).child("My_Created_Events").child(event_name).child("Customers").child(uid_ac20).removeValue();
                                  Toast.makeText(MainActivity20.this, "Event Cancelled Successfully !", Toast.LENGTH_SHORT).show();
                                  break;
                              }
                              if(cns==1){
                                  break;
                              }
                          }
                          if(cns == 0){
                              Toast.makeText(MainActivity20.this, "Couldn't Cancel !", Toast.LENGTH_SHORT).show();
                          }
                      }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            }
        });

    }
    public void givePayStatus(String uid,String evname,String evid){

            dDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
            dDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int ans = 0;
                     for(DataSnapshot b : snapshot.getChildren()){
                         for(DataSnapshot c : b.child("My_Created_Events").getChildren()){
                               String inname = c.getKey().toString();
                               String inid = c.child("Eventid").getValue().toString();
                               if(evname.equals(inname) && evid.equals(inid)){
                                     ans = 1;
                                     String take_val = c.child("Customers").child(uid).getValue().toString();
                                     if(take_val.equals("1")){
                                         tv202.setText("Payment Status :- Not Done .");
                                     }
                                     else if(take_val.equals("2")){
                                         tv202.setText("Payment Status :- Done .");
                                     }
                                     else if(take_val.equals("0")){
                                         tv202.setText("You are removed from the event !");
                                     }
                                     break;
                               }
                         }
                         if(ans==1){
                             break;
                         }
                     }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

    }

}
