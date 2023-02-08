package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity14 extends AppCompatActivity {
    TextView tv141,tv142,tv143,tv144,tv145,tv146,tv147;
    Button b141;
    public String event_name;
    public String event_id;
    public String cust_uid;
    private DatabaseReference dDatabase;
    private DatabaseReference eDatabase;
    public String save_cust_uid;
    public String save_ev_name;
    public String gpass;
    public int ef = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        b141 = findViewById(R.id.b141);
        tv141 = findViewById(R.id.tv141);
        tv142 = findViewById(R.id.tv142);
        tv143 = findViewById(R.id.tv143);
        tv144 = findViewById(R.id.tv144);
        tv145 = findViewById(R.id.tv145);
        tv146 = findViewById(R.id.tv146);
        tv147 = findViewById(R.id.tv147);
        Intent ac141 = getIntent();
        event_name = ac141.getStringExtra(MainActivity13.EVNAME);
        event_id = ac141.getStringExtra(MainActivity13.EVID);
        cust_uid = ac141.getStringExtra(MainActivity13.UID13);
        dDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        dDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int ans = 0;
                for (DataSnapshot sp : snapshot.getChildren()){
                    save_cust_uid = sp.getKey().toString();
                    for(DataSnapshot ss : sp.child("My_Created_Events").getChildren()){
                          String ch_evname = ss.getKey().toString();
                          String ch_evid = ss.child("Eventid").getValue().toString();
                          if(ch_evid.equals(event_id) && ch_evname.equals(event_name)){
                              save_ev_name = ch_evname;
                              ans = 1;
                              tv141.setText(ch_evname);
                              String org_by = ss.child("Eventorg").getValue().toString();
                              String ch_date = ss.child("Eventdate").getValue().toString();
                              String ch_time = ss.child("Eventtime").getValue().toString();
                              String ch_cont = ss.child("Eventcontact").getValue().toString();
                              String ch_passes = ss.child("Eventpasses").getValue().toString();
                              String ch_venue = ss.child("Eventvenue").getValue().toString();
                              tv142.setText(org_by);
                              tv143.setText(ch_cont);
                              tv144.setText(ch_date);
                              tv145.setText(ch_time);
                              tv146.setText(ch_passes);
                              tv147.setText(ch_venue);
                               break;
                          }
                    }
                    if(ans==1){
                        break;
                    }
                }
                if(ans==0){
                    Toast.makeText(MainActivity14.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        b141.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ef == 0) {
                    eDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                    eDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String epasses = snapshot.child(save_cust_uid).child("My_Created_Events").child(save_ev_name).child("Eventpasses").getValue().toString();
                            String econ = snapshot.child(save_cust_uid).child("My_Created_Events").child(save_ev_name).child("Eventcontact").getValue().toString();
                            String edate = snapshot.child(save_cust_uid).child("My_Created_Events").child(save_ev_name).child("Eventdate").getValue().toString();
                            String etime = snapshot.child(save_cust_uid).child("My_Created_Events").child(save_ev_name).child("Eventtime").getValue().toString();
                            String evenue = snapshot.child(save_cust_uid).child("My_Created_Events").child(save_ev_name).child("Eventvenue").getValue().toString();
                            int passes = Integer.parseInt(epasses);
                            int koi = passes - 1;
                            String epass = Integer.toString(koi);
                            gpass = epass;
                            if (passes > 0 && ef == 0) {
                                Toast.makeText(MainActivity14.this, "Event Booked Successfully !", Toast.LENGTH_SHORT).show();
                                ef = 1;
                                Log.d("Hii", gpass);
                                eDatabase.child(save_cust_uid).child("My_Created_Events").child(save_ev_name).child("Customers").child(cust_uid).setValue("1");
                                eDatabase.child(save_cust_uid).child("My_Created_Events").child(save_ev_name).child("Eventpasses").setValue(gpass);
                                eDatabase.child(cust_uid).child("My_Booked_Events").child(event_name).child("Eventid").setValue(event_id);
                                eDatabase.child(cust_uid).child("My_Booked_Events").child(event_name).child("Eventcontact").setValue(econ);
                                eDatabase.child(cust_uid).child("My_Booked_Events").child(event_name).child("Eventdate").setValue(edate);
                                eDatabase.child(cust_uid).child("My_Booked_Events").child(event_name).child("Eventtime").setValue(etime);
                                eDatabase.child(cust_uid).child("My_Booked_Events").child(event_name).child("Eventvenue").setValue(evenue);
                                Intent ac141 = new Intent(MainActivity14.this, MainActivity8.class);
                                startActivity(ac141);
                            } else if (ef == 0) {
                                ef = 2;
                                Toast.makeText(MainActivity14.this, "Events Passes Sold or Bookings Closed", Toast.LENGTH_SHORT).show();
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