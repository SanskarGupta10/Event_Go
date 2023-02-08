package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class MainActivity2 extends AppCompatActivity {
    TextView tv211,tv212;
    public int ans;
    Button b233;
    EditText ed211;
    DatabaseReference fDatabase;
    DatabaseReference lDatabase;
    DatabaseReference yDatabase;
    ListView lv211;
    public String evname_ac21;

    public static final String UIDAC2="sds";
    public static final String MEREUID = "Sa";
    public static final String MERAEVENT = "sa";
    public String uid_ac21;
    public String search_uid;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
        tv211 = findViewById(R.id.tv211);
        tv212 = findViewById(R.id.tv212);
        lv211 = findViewById(R.id.lv211);
        b233 = findViewById(R.id.b233);
        ed211 = findViewById(R.id.ed211);
        Intent ac211 = getIntent();
        evname_ac21 = ac211.getStringExtra(MainActivity12.EVAC12);
        uid_ac21 = ac211.getStringExtra(MainActivity12.UIDAC12);
        ArrayList<String> myListi = new ArrayList<String>();
        fDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        fDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot a : snapshot.child(uid_ac21).child("My_Created_Events").child(evname_ac21).child("Customers").getChildren()){
                    String cust_id_ac = a.getKey().toString();
                    myListi.add("\nUID :- "+cust_id_ac+"\n");
                }
                ArrayAdapter myAdapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_list_item_1,myListi);
                lv211.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        lv211.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = lv211.getAdapter().getItem(i);
                String take_uid = obj.toString();
                Intent ac215 = new Intent(MainActivity2.this,MainActivity22.class);
                ac215.putExtra(UIDAC2,take_uid);
                ac215.putExtra(MEREUID,uid_ac21);
                ac215.putExtra(MERAEVENT,evname_ac21);
                startActivity(ac215);
            }
        });
        b233.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> st = new ArrayList<String>();
                String take_uid_me = ed211.getText().toString();
                if(take_uid_me.isEmpty()){
                    Toast.makeText(MainActivity2.this, "Enter the UID first !", Toast.LENGTH_SHORT).show();
                }
                else {
                     ans = 0;
                    yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                    yDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                              for(DataSnapshot ab : snapshot.child(uid_ac21).child("My_Created_Events").child(evname_ac21).child("Customers").getChildren()){
                                        String input_uid = ab.getKey().toString();
                                        if(take_uid_me.equals(input_uid)){
                                            ans = 1;
                                            st.add("\nUID :-"+take_uid_me+"\n");
                                            ArrayAdapter myAdapter2 = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,st);
                                            lv211.setAdapter(myAdapter2);
                                            break;
                                        }
                              }
                              if(ans==0){
                                  Toast.makeText(MainActivity2.this, "User UID not present in the booking list !", Toast.LENGTH_SHORT).show();
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