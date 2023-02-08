package com.example.event_go;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity7 extends AppCompatActivity {
     Button b71;
     TextView t71,t72;
     ImageView iv71;
     DatabaseReference mDatabase;
    public static final int REQUEST_IMAGE_GET = 1;
    public Uri fullPhotoUri;
    public String uid;
    public String created_url;
    public static final String MID = "jkmkc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        b71 = findViewById(R.id.b71);
        t71 = findViewById(R.id.t71);
        t72 = findViewById(R.id.t72);
        iv71 = findViewById(R.id.iv71);
        Intent ac7 = getIntent();
        uid = ac7.getStringExtra(MainActivity6.UID);
        iv71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                }
                if (fullPhotoUri != null) {
                    uploadToFirebase(fullPhotoUri);
                } else {
                    Toast.makeText(MainActivity7.this, "No image url !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            iv71.setImageURI(data.getData());
            fullPhotoUri = data.getData();
            Toast.makeText(MainActivity7.this, "Uploading image :- " + fullPhotoUri.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    private void uploadToFirebase(Uri muri) {
        StorageReference mStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://eventgo-8ce8b.appspot.com").child(uid).child(uid + ".idproof");
        mStorage.putFile(muri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        created_url = uri.toString();
                        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                        mDatabase.child(uid).child("Userimageurl").setValue(created_url);
                        Toast.makeText(MainActivity7.this,"Saving Successfylly !",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity7.this, "Uploading Failed ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void saveandcont(View view){
        if(fullPhotoUri == null){
            Toast.makeText(this, "Select the image first and then continue !", Toast.LENGTH_SHORT).show();
        }
        else{

            Intent ac72=new Intent(MainActivity7.this,MainActivity8.class);
            ac72.putExtra(MID,uid);
            startActivity(ac72);
        }
    }
}