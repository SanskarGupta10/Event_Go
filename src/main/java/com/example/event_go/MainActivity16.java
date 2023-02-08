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

public class MainActivity16 extends AppCompatActivity {
    TextView tv161;
    EditText ed161,ed162,ed163;
    Button b161;
    public String uid_ac16;
    DatabaseReference mDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        tv161 = findViewById(R.id.tv161);
        ed161 = findViewById(R.id.ed161);
        ed162 = findViewById(R.id.ed162);
        ed163 = findViewById(R.id.ed163);
        b161 = findViewById(R.id.b161);
        Intent ac161 = getIntent();
        uid_ac16 = ac161.getStringExtra(MainActivity15.UID_AC);
        b161.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String cur_pass = ed161.getText().toString();
               String new_pass = ed162.getText().toString();
               String new_pass2 = ed163.getText().toString();
               mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
               mDatabase.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                     String f_cur_pass = snapshot.child(uid_ac16).child("Userpasswd").getValue().toString();
                     if(cur_pass.isEmpty()||new_pass.isEmpty()||new_pass2.isEmpty()){
                         Toast.makeText(MainActivity16.this, "Enter All the Required Fields !", Toast.LENGTH_SHORT).show();
                     }
                     else{
                     if(f_cur_pass.equals(cur_pass)){
                         if(new_pass.equals(new_pass2)){
                             mDatabase.child(uid_ac16).child("Userpasswd").setValue(new_pass);
                             Toast.makeText(MainActivity16.this, "Password Changed Successfully !", Toast.LENGTH_SHORT).show();
                         }
                         else{
                             Toast.makeText(MainActivity16.this, "New password do not matching !", Toast.LENGTH_SHORT).show();
                         }
                     }
                     else{
                         Toast.makeText(MainActivity16.this, "Enter the current password correctly !", Toast.LENGTH_SHORT).show();
                     }
                   }}

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }
        });
    }
}