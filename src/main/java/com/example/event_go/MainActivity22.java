package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity22 extends AppCompatActivity {
      TextView tv221,tv222,tv223,tv224,tv225;
      DatabaseReference gDatabase;
      DatabaseReference xDatabase;
      DatabaseReference pDatabase;
      DatabaseReference qDatabase;
      public static final String MUIDAC22 = "as";
      public static final String MEVNAC22 = "saa";
      public static final String CUSTIDAC22 = "ax";
      public String uidac22;
      public String mereuid;
      public String meraevent;
      public String custuid;
      Button b221,b222,b223;
      public int ef = 0;
      public int ec = 0;
      EditText ed221;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        tv221 = findViewById(R.id.tv221);
        tv222 = findViewById(R.id.tv222);
        tv223 = findViewById(R.id.tv223);
        tv224 = findViewById(R.id.tv224);
        tv225 = findViewById(R.id.tv225);
        ed221 = findViewById(R.id.ed221);
        b221 = findViewById(R.id.b231);
        b222 = findViewById(R.id.b222);
        b223 = findViewById(R.id.b223);
        Intent ac22 = getIntent();
        uidac22 = ac22.getStringExtra(MainActivity2.UIDAC2);
        mereuid = ac22.getStringExtra(MainActivity2.MEREUID);
        meraevent = ac22.getStringExtra(MainActivity2.MERAEVENT);
        gDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        gDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for(DataSnapshot a : snapshot.getChildren()){
                       String take_myuid = a.getKey().toString();
                       String final_myuid = "\nUID :- "+take_myuid+"\n";
                       if(final_myuid.equals(uidac22)){
                           custuid = take_myuid;
                           String u_name = a.child("Username").getValue().toString();
                           String u_phone = a.child("Userphone").getValue().toString();
                           String u_cntry = a.child("Usercountry").getValue().toString();
                           String u_imgurl = a.child("Userimageurl").getValue().toString();
                           String valid_int_3 = snapshot.child(mereuid).child("My_Created_Events").child(meraevent).child("Customers").child(custuid).getValue().toString();
                           if(valid_int_3.equals("0")){
                               tv225.setText("This User is Blocked");
                           }
                           else if(valid_int_3.equals("1")){
                               tv225.setText("Payment Status :- Not Done .");
                           }
                           else if(valid_int_3.equals("2")){
                               tv225.setText("Payment Status :- Done .");
                           }
                           else{
                               tv225.setText("Event Not Even Booked !");
                           }
                           tv222.setText("User Name :- "+u_name);
                           tv223.setText("User Contact :- "+u_phone);
                           tv224.setText("User Country :- "+u_cntry);
                           if(!u_imgurl.equals("")){
                               ed221.setText("Id Proof pic URL :- "+u_imgurl);
                           }
                             break;
                       }
                   }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
             b221.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     pDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                     pDatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                              String valid_int = snapshot.child(mereuid).child("My_Created_Events").child(meraevent).child("Customers").child(custuid).getValue().toString();
                              if(valid_int.equals("2")){
                                  Toast.makeText(MainActivity22.this, "You can't block the user after receiving the payment , It's against our policies !", Toast.LENGTH_SHORT).show();
                              }
                              else if(ec==0){
                                  ec=1;
                                  pDatabase.child(mereuid).child("My_Created_Events").child(meraevent).child("Customers").child(custuid).setValue("0");
                                  Toast.makeText(MainActivity22.this, "This User is Blocked Now !", Toast.LENGTH_SHORT).show();
                              }
                              else if(ec==1){
                                  Toast.makeText(MainActivity22.this, "Couldn't block the user more than once !", Toast.LENGTH_SHORT).show();
                              }
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });


                 }
             });
        b223.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                qDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String valid_int_2 = snapshot.child(mereuid).child("My_Created_Events").child(meraevent).child("Customers").child(custuid).getValue().toString();
                        if(valid_int_2.equals("0") && ef==0){
                            ef=1;
                            qDatabase.child(mereuid).child("My_Created_Events").child(meraevent).child("Customers").child(custuid).setValue("1");
                            Toast.makeText(MainActivity22.this, "This User is Unblocked Now, Remember you can unblock the user of a particular event only once  !", Toast.LENGTH_SHORT).show();
                        }
                        else if(ef==0){
                            Toast.makeText(MainActivity22.this, "User is not blocked !", Toast.LENGTH_SHORT).show();
                        }
                        else if(ef==1){
                            Toast.makeText(MainActivity22.this, "Couldn't Unblock the user more than once !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        b222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                xDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                xDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String valid_int_4 = snapshot.child(mereuid).child("My_Created_Events").child(meraevent).child("Customers").child(custuid).getValue().toString();
                        if(valid_int_4.equals("2")){
                            Toast.makeText(MainActivity22.this, "Payment status already marked done !", Toast.LENGTH_SHORT).show();
                        }
                        else if(valid_int_4.equals("0")){
                            Toast.makeText(MainActivity22.this, "User is Blocked from this event !", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent ac223 = new Intent(MainActivity22.this,MainActivity23.class);
                            ac223.putExtra(MUIDAC22,mereuid);
                            ac223.putExtra(MEVNAC22,meraevent);
                            ac223.putExtra(CUSTIDAC22,custuid);
                            startActivity(ac223);
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