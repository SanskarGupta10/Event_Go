package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity13 extends AppCompatActivity {
      TextView tv131;
      ImageView iv131;
      Button b131;
      EditText ed131,ed132;
      DatabaseReference cDatabase;
      public String uid13;
      public static final String UID13 = "cczx";
      public static final String UNIQNAME = "sads";
      public static final String EVNAME = "ASa";
      public static final String EVID = "xsa";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        tv131 = findViewById(R.id.tv131);
        b131 = findViewById(R.id.b131);
        ed131 = findViewById(R.id.ed131);
        iv131 = findViewById(R.id.iv131);
        ed132 = findViewById(R.id.ed132);
        Intent ac131 = getIntent();
        uid13 = ac131.getStringExtra(MainActivity8.UID_C);
        b131.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String in_ename = ed131.getText().toString();
                String in_eid = ed132.getText().toString();
                 cDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                 cDatabase.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         int ans = 0;
                         int f = 0;
                              for(DataSnapshot sp : snapshot.getChildren()){
                                  for(DataSnapshot ss : sp.child("My_Created_Events").getChildren()){
                                      String u_id = sp.getKey().toString();
                                      String b_name = ss.getKey().toString();
                                      String b_id = ss.child("Eventid").getValue().toString();
                                      if(b_name.equals(in_ename) && b_id.equals(in_eid)){
                                          for(DataSnapshot sc : ss.child("Customers").getChildren()){
                                              String cust_id = sc.getKey().toString();
                                              if(cust_id.equals(uid13)){
                                                  f=1;
                                                  ans = 2;
                                                  Toast.makeText(MainActivity13.this, "Event Already Booked . Please Do not press any button until the warning appears !", Toast.LENGTH_SHORT).show();
                                                  ed131.setText("");
                                                  ed132.setText("");
                                                  break;
                                              }
                                          }
                                          if(f==0){
                                              ans = 1;
                                              Intent ac131 = new Intent(MainActivity13.this,MainActivity14.class);
                                              ac131.putExtra(UID13,uid13);
                                              ac131.putExtra(UNIQNAME,u_id);
                                              ac131.putExtra(EVNAME,b_name);
                                              ac131.putExtra(EVID,b_id);
                                              startActivity(ac131);
                                              break;
                                          }
                                      }
                                  }
                                  if(ans==1){
                                      break;
                                  }
                              }
                              if(ans==0){
                                  Toast.makeText(MainActivity13.this, "Enter Correct Event Details !", Toast.LENGTH_SHORT).show();
                                  ed131.setText("");
                                  ed132.setText("");

                              }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });
            }
        });
    }
}