package com.example.finalyearapp.RecycleMaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finalyearapp.MainActivity;
import com.example.finalyearapp.R;
import com.example.finalyearapp.Register;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.finalyearapp.MainActivity.KEY1;


public class Materialview extends AppCompatActivity {


    TextView what , how , tip , where, title;
    ImageButton facebook, twitter , linkedIn;
    Button home, listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialview);

        what = (TextView) findViewById(R.id.what);
        how = (TextView) findViewById(R.id.How);
        where = (TextView) findViewById(R.id.Where);
        title = (TextView) findViewById(R.id.Title);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Materials").child(String.valueOf(name));
        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Material material = dataSnapshot.getValue(Material.class);

                title.setText(material.getName());
                what.setText(material.getWhat());
                where.setText(material.getWhere());
                how.setText(material.getGeneralComment());



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        facebook = (ImageButton) findViewById(R.id.Facebook);
        facebook.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = title.getText().toString() + "/n" + what.getText().toString()  + "/n" + where.getText().toString()  + "/n" + how.getText().toString()  + "/n" + tip.getText().toString();
                String shareSub = "Your subject here";

                intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                intent.putExtra(Intent.EXTRA_TEXT, shareSub);

                startActivity(Intent.createChooser(intent,"Share using"));
            }


        });
        twitter = (ImageButton) findViewById(R.id.Twitter);
        twitter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = title + "/n" + what  + "/n" + where  + "/n" + how  + "/n" + tip;
                String shareSub = "Your subject here";

                intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                intent.putExtra(Intent.EXTRA_TEXT, shareSub);

                startActivity(Intent.createChooser(intent,"Share using"));
            }


        });
        home = (Button) findViewById(R.id.Home);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Materialview.this , Menu.class);
                startActivity(intent1);
            }
        });

        listview = (Button) findViewById(R.id.ItemList);
        listview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Materialview.this , recyclewhat.class);
                startActivity(intent);
            }
        });

    }
}
