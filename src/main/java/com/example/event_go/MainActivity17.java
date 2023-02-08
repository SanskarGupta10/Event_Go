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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.TimeUnit;

public class MainActivity17 extends AppCompatActivity {
       TextView tv171,tv172;
       Button b171,b172;
       EditText ed171;
       FirebaseAuth mauth;
       DatabaseReference qdatabase;
       DatabaseReference mDatabase;
       StorageReference myStorage;
       String verifyId;
       public String uid_ac17;
       public String phnum;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        tv171 = findViewById(R.id.tv171);
        tv172 = findViewById(R.id.tv172);
        b171 = findViewById(R.id.b171);
        b172 = findViewById(R.id.b172);
        ed171 = findViewById(R.id.ed171);
        mauth = FirebaseAuth.getInstance();
        Intent ac171 = getIntent();
        uid_ac17 = ac171.getStringExtra(MainActivity15.UID_AC);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phnum = snapshot.child(uid_ac17).child("Userphone").getValue().toString();
                tv172.setText(phnum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        b171.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtp(phnum);
            }
        });
        b172.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed171.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity17.this, "Enter OTP first to delete the account !", Toast.LENGTH_SHORT).show();
                }
                else{
                    verifyDelete(ed171.getText().toString());
                }
            }
        });
    }
    public void sendOtp(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mauth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(MainActivity17.this, "Verification Failed !", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(verificationId, token);
            verifyId = verificationId;

        }
    };

    public void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyId, code);
        signInByCredential(credential);
    }

    public void verifyDelete(String code) {
        PhoneAuthCredential cred = PhoneAuthProvider.getCredential(verifyId, code);
        signInByCred(cred);
    }

    private void signInByCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = task.getResult().getUser();
                    String my_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Toast.makeText(MainActivity17.this, "Moving to next step !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInByCred(PhoneAuthCredential cred) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(cred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser users = task.getResult().getUser();
                    String my_uid_l = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    myStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://eventgo-8ce8b.appspot.com").child(my_uid_l);
                    myStorage.child(my_uid_l+".idproof").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity17.this, "Deleting the Storage !", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity17.this, "Sorry , Unable to delete the storage !", Toast.LENGTH_SHORT).show();
                        }
                    });
                    qdatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                    qdatabase.child(my_uid_l).removeValue();

                    FirebaseUser my_user = firebaseAuth.getCurrentUser();
                    if (my_user != null) {
                        my_user.reauthenticate(cred).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                my_user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MainActivity17.this, "Account Deleted Successfully !", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });

    }
}