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
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity10 extends AppCompatActivity {
    Button b101,b102;
    TextView tv101,tv102;
    EditText ed101;
    String verifyId;
    public String phone_number;
    FirebaseAuth mauth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        tv101 = findViewById(R.id.tv101);
        tv102 = findViewById(R.id.tv102);
        b101 = findViewById(R.id.b101);
        ed101 = findViewById(R.id.ed101);
        b102 = findViewById(R.id.b102);

        Intent ac101 = getIntent();
        phone_number = ac101.getStringExtra(MainActivity4.MY_PN);
        tv102.setText(phone_number);
        mauth = FirebaseAuth.getInstance();
        b101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtp(phone_number);
            }
        });
        b102.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed101.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity10.this, "Enter the OTP first to verify user !", Toast.LENGTH_SHORT).show();
                }
                else {
                    verifyCode(ed101.getText().toString());
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
            Toast.makeText(MainActivity10.this, "Verification Failed !", Toast.LENGTH_SHORT).show();
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

                    Intent nintent = new Intent(MainActivity10.this, MainActivity8.class);

                    startActivity(nintent);
                    Toast.makeText(MainActivity10.this, "Moving to next step !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity10.this, "OTP not matching !", Toast.LENGTH_SHORT).show();
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