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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4 extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText ed41,ed42,ed43;
    Button b41;
    ImageView iv41;
    public String mypn;
    public static final String MY_PN="jmd";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ed41 = findViewById(R.id.ed41);
        ed42 = findViewById(R.id.ed42);
        ed43 = findViewById(R.id.ed43);
        b41 = findViewById(R.id.b41);
        iv41 = findViewById(R.id.iv41);
        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cncode = ed41.getText().toString();
                String cphn = ed42.getText().toString();
                String fnum = "+"+cncode+cphn;
                mypn = fnum;
                String cpasswd = ed43.getText().toString();
                mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int t = 0;
                        for(DataSnapshot sp : snapshot.getChildren()){
                         String fphn = sp.child("Userphone").getValue().toString();
                         String fpasswd = sp.child("Userpasswd").getValue().toString();
                         if(fphn.equals(fnum) && fpasswd.equals(cpasswd)){
                             t = 1;
                             Toast.makeText(MainActivity4.this, "Logging in successfully !", Toast.LENGTH_SHORT).show();
                             Intent ac4 = new Intent(MainActivity4.this,MainActivity10.class);
                             ac4.putExtra(MY_PN,mypn);
                             startActivity(ac4);
                             break;
                         }
                        }
                        if(t==0){
                            Toast.makeText(MainActivity4.this, "Sorry, Not a valid credentials !", Toast.LENGTH_SHORT).show();
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