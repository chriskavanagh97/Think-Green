package com.example.finalyearapp;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.finalyearapp.Carbonfootprint.StepOne;


import com.example.finalyearapp.ClimateChange.Didyouknow;

import com.example.finalyearapp.Maps.Instructions;
import com.bumptech.glide.Glide;




import com.example.finalyearapp.News.NewsSelection;
import com.example.finalyearapp.QuizMenuPackage.QuizMenu;
import com.example.finalyearapp.RecycleMaterial.recyclemenu;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import java.util.Objects;


import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.finalyearapp.MainActivity.KEY1;
import static java.security.AccessController.getContext;


public class MainMenu extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    String userid = mFirebaseAuth.getCurrentUser().getUid();
    DatabaseReference reference;

    RelativeLayout maincontent;
    LinearLayout mainmenu;
    ImageView News, map;
    CircleImageView profileimage;
    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;


    private Uri imageuri;
    private StorageTask uploadTask;


    FirebaseUser fuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String email = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getEmail();

        profileimage = findViewById(R.id.profile_image);

        storageReference = FirebaseStorage.getInstance().getReference("upload");
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openImage();
            }
        });


        maincontent = (RelativeLayout) findViewById(R.id.mainContent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.toolbar));


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent.animate().translationX(0);
                mainmenu.animate().translationX(0);


            }
        });
        News = (ImageView) findViewById(R.id.News);
        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, NewsSelection.class);

                startActivity(intent);
            }
        });
        map = (ImageView) findViewById(R.id.maps);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Instructions.class);
                intent.putExtra("value", 3);

                startActivity(intent);
            }
        });
        maincontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maincontent.animate().translationX(-510);
                mainmenu.animate().translationX(-510);
            }
        });
        //=========================================================================================================================================================================================
        //reading from the Database
        //=========================================================================================================================================================================================


        //=========================================================================================================================================================================================
        //Setting the text box with info from the database
        //=========================================================================================================================================================================================

        ImageView quiz = (ImageView) findViewById(R.id.Quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(MainMenu.this, QuizMenu.class);
                startActivity(inttoQuiz);

            }
        });
        ImageView carbonfootprint = (ImageView) findViewById(R.id.Carbonfootprint);
        carbonfootprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent footprint = new Intent(MainMenu.this, StepOne.class);
                startActivity(footprint);

            }
        });

        //=========================================================================================================================================================================================
        //Sign Out
        //=========================================================================================================================================================================================

        Button signout = (Button) findViewById(R.id.SignOut);
        signout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(MainMenu.this, HomeScreen.class);
                startActivity(intToMain);

            }
        });

        //=========================================================================================================================================================================================
        //Writing a new score to the database
        //=========================================================================================================================================================================================
       /* Button scoreupdate = (Button) findViewById(R.id.ScoreUpdate);
        scoreupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {

                score = 100;




            }
        });*/

        //=========================================================================================================================================================================================
        //Switching to the profile page
        //=========================================================================================================================================================================================
        Button profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        ViewUser.class);

                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        intent.getStringExtra(KEY1);


        ImageView climatechange = (ImageView) findViewById(R.id.climatechange);
        climatechange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        Didyouknow.class);

                startActivity(intent);
            }
        });
        ImageView recycle = (ImageView) findViewById(R.id.recycle);
        recycle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        recyclemenu.class);

                startActivity(intent);
            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);






                    Glide.with(MainMenu.this.getApplicationContext()).load(user.getImageURL()).into(profileimage);




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


        ContentResolver contentResolver = MainMenu.this.getApplicationContext().getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }


    private void uploadImage() {


        final ProgressDialog pd = new ProgressDialog(MainMenu.this.getApplicationContext());

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


                        Toast.makeText(MainMenu.this.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                        pd.dismiss();

                    }

                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override

                public void onFailure(@NonNull Exception e) {


                    Toast.makeText(MainMenu.this.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    pd.dismiss();

                }

            });


        } else {


            Toast.makeText(MainMenu.this.getApplicationContext(), "No Image Selected", Toast.LENGTH_SHORT).show();

        }

    }


    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK

                && data != null && data.getData() != null) {


            imageuri = data.getData();


            if (uploadTask != null && uploadTask.isInProgress()) {


                Toast.makeText(MainMenu.this.getApplicationContext(), "Upload in Progress", Toast.LENGTH_SHORT).show();

            } else {


                uploadImage();

            }


        }
    }
}


