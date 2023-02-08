package com.example.event_go;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
public class MainActivity6 extends AppCompatActivity {
     EditText ed61,ed62,ed63;
     TextView t61;
     Button b61;
     ImageView iv61;
     private DatabaseReference  mDatabase;
     public String uniq_id,pnum;
     public static final String UID = "tmkc";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ed61 = findViewById(R.id.ed61);
        ed62 = findViewById(R.id.ed62);
        ed63 = findViewById(R.id.ed63);
        t61 = findViewById(R.id.t61);
        b61 = findViewById(R.id.b61);
        iv61 = findViewById(R.id.iv61);
        Intent intent = getIntent();
        uniq_id = intent.getStringExtra(MainActivity5.UN_ID);
        pnum = intent.getStringExtra(MainActivity5.PHONE_NUMBER);
        t61.setText(pnum);
        Toast.makeText(this, uniq_id, Toast.LENGTH_SHORT).show();
        b61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String uname = ed61.getText().toString();
              String upasswd = ed62.getText().toString();
              String ucntry = ed63.getText().toString();
              if(uname.isEmpty() || upasswd.isEmpty() || ucntry.isEmpty()){
                  Toast.makeText(MainActivity6.this, "Enter all the fields and try again !", Toast.LENGTH_SHORT).show();
              }
              else{
                  mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                  mDatabase.child(uniq_id).child("Username").setValue(uname);
                  mDatabase.child(uniq_id).child("Userpasswd").setValue(upasswd);
                  mDatabase.child(uniq_id).child("Userphone").setValue(pnum);
                  mDatabase.child(uniq_id).child("Usercountry").setValue(ucntry);
                  mDatabase.child(uniq_id).child("Userimageurl").setValue("");
                  Intent ac6= new Intent(MainActivity6.this,MainActivity7.class);
                  ac6.putExtra(UID,uniq_id);
                  startActivity(ac6);

              }
            }
        });
    }
}