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

public class MainActivity18 extends AppCompatActivity {
     Button b181;
     TextView t181,t182;
     ImageView iv181;
     public Uri fullPhotoUri2;
     public String uid_ac18;
     public String created_url2;
      DatabaseReference zDatabase;
    public static final int REQUEST_IMAGE_GET = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);
        b181 = findViewById(R.id.b181);
        t181 = findViewById(R.id.t181);
        t182 = findViewById(R.id.t182);
        iv181 = findViewById(R.id.iv181);
        Intent ac181 = getIntent();
        uid_ac18 = ac181.getStringExtra(MainActivity15.UID_AC);
        iv181.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            iv181.setImageURI(data.getData());
            fullPhotoUri2 = data.getData();
            Toast.makeText(MainActivity18.this, "Uploading image :- " + fullPhotoUri2.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void uploadToFirebase2(Uri muri) {
        Toast.makeText(this,uid_ac18, Toast.LENGTH_SHORT).show();
        StorageReference zStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://eventgo-8ce8b.appspot.com").child(uid_ac18).child(uid_ac18 + ".idproof");
        zStorage.putFile(muri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                zStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        created_url2 = uri.toString();
                        zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eventgo-8ce8b-default-rtdb.firebaseio.com/");
                        zDatabase.child(uid_ac18).child("Userimageurl").setValue(created_url2);
                        Toast.makeText(MainActivity18.this,"Saving Successfylly !",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity18.this, "Uploading Failed ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void saveandconst(View view){
        if(fullPhotoUri2 == null){
            Toast.makeText(this, "Select the image first and then continue !", Toast.LENGTH_SHORT).show();
        }
        else{
            uploadToFirebase2(fullPhotoUri2);
            Intent ac182=new Intent(MainActivity18.this,MainActivity15.class);
            startActivity(ac182);
        }
    }
}