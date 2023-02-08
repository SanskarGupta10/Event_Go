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

import java.util.concurrent.TimeUnit;

import android.view.View;

public class MainActivity5 extends AppCompatActivity {
   EditText ed51,ed52,ed53;
    FirebaseAuth mauth;
    String verifyId;
    public String phno;
    public static final String UN_ID = "sds";
    public static final String PHONE_NUMBER = "number";

    Button b51,b52;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ed51 = findViewById(R.id.ed51);
        ed52 = findViewById(R.id.ed52);
        ed53 = findViewById(R.id.ed53);
        b51 = findViewById(R.id.b51);
        b52 = findViewById(R.id.b52);

        mauth = FirebaseAuth.getInstance();
        b51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed53.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity5.this, "Enter the otp first", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCode(ed53.getText().toString());
                }
            }
        });
        b52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ccode = ed51.getText().toString();
                String pnumber = ed52.getText().toString();
                String fnumber = "+"+ccode+pnumber;
                phno = fnumber;
                if(ccode.length() != 2){
                    Toast.makeText(MainActivity5.this,"Enter Valid Country Code and Try again !",Toast.LENGTH_SHORT).show();
                }
                if(ccode.isEmpty() || pnumber.isEmpty()){
                    Toast.makeText(MainActivity5.this,"Enter country code and phone number then Try again !",Toast.LENGTH_SHORT).show();
                }
                else{
                    sendOtp(fnumber);
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
            Toast.makeText(MainActivity5.this, "Verification Failed !", Toast.LENGTH_SHORT).show();
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

                    Intent nintent = new Intent(MainActivity5.this, MainActivity6.class);
                    nintent.putExtra(PHONE_NUMBER, phno);
                    nintent.putExtra(UN_ID, my_uid);
                    startActivity(nintent);
                    Toast.makeText(MainActivity5.this, "Moving to next step !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity5.this, "OTP not matching !", Toast.LENGTH_SHORT).show();
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

                }
            }
        });

    }
}