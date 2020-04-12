package com.example.finalyearapp;



import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewUser extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    String userid = mFirebaseAuth.getCurrentUser().getUid();
    DatabaseReference reference;

    CircleImageView profileimage;
    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;


    private Uri imageuri;
    private StorageTask uploadTask;

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        // home = (Button) findViewById(R.id.home);
        final TextView R1,R2,R3,R4;
        profileimage = findViewById(R.id.profile_image);

        storageReference = FirebaseStorage.getInstance().getReference("upload");
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openImage();
            }
        });



        R1 = (TextView) findViewById(R.id.Nameresult);
        R2 = (TextView) findViewById(R.id.Usernameresult);
        R3 = (TextView) findViewById(R.id.Emailresult);

        R4 = (TextView) findViewById(R.id.ScoreResult );

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String userid = mFirebaseAuth.getCurrentUser().getUid();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(userid));
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                R1.setText(user.getFirstName());
                R2.setText(user.getUsername());
                R3.setText(user.getEmail());
                R4.setText(String.valueOf((user.getScore())));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);






                Glide.with(ViewUser.this.getApplicationContext()).load(user.getImageURL()).into(profileimage);




            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    private void openImage() {


        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, IMAGE_REQUEST);

    }


    private String getFileExtension(Uri uri) {


        ContentResolver contentResolver = ViewUser.this.getApplicationContext().getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }


    private void uploadImage() {


        final ProgressDialog pd = new ProgressDialog(ViewUser.this.getApplicationContext());

        pd.setMessage("Uploading");




        if (imageuri != null) {


            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()

                    + "." + getFileExtension(imageuri));


            uploadTask = fileReference.putFile(imageuri);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {


                @Override

                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {


                    if (!task.isSuccessful()) {


                        throw task.getException();

                    }


                    return fileReference.getDownloadUrl();

                }

            }).addOnCompleteListener(new OnCompleteListener<Uri>() {

                @Override

                public void onComplete(@NonNull Task<Uri> task) {


                    if (task.isSuccessful()) {


                        Uri downloadUri = task.getResult();

                        String mUri = downloadUri.toString();


                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);


                        HashMap<String, Object> map = new HashMap<>();

                        map.put("imageURL", mUri);

                        reference.updateChildren(map);


                        pd.dismiss();

                    } else {


                        Toast.makeText(ViewUser.this.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                        pd.dismiss();

                    }

                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override

                public void onFailure(@NonNull Exception e) {


                    Toast.makeText(ViewUser.this.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    pd.dismiss();

                }

            });


        } else {


            Toast.makeText(ViewUser.this.getApplicationContext(), "No Image Selected", Toast.LENGTH_SHORT).show();

        }

    }


    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK

                && data != null && data.getData() != null) {


            imageuri = data.getData();


            if (uploadTask != null && uploadTask.isInProgress()) {


                Toast.makeText(ViewUser.this.getApplicationContext(), "Upload in Progress", Toast.LENGTH_SHORT).show();

            } else {


                uploadImage();

            }


        }
    }
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }
}
