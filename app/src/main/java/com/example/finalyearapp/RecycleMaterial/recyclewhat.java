package com.example.finalyearapp.RecycleMaterial;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.finalyearapp.News.ArticlesActivity;
import com.example.finalyearapp.News.ArticlesItem;
import com.example.finalyearapp.R;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class recyclewhat extends AppCompatActivity {




    String Where, why, generalcomment, how, what;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();


    String userid = mFirebaseAuth.getCurrentUser().getUid();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MaterialFavourites").child(userid);

    DatabaseReference mRootRef1 = FirebaseDatabase.getInstance().getReference().child("Materials");
    private ArrayList<Material> materials = new ArrayList<>();


    private RecyclerView recyclerView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclewhat);

        recyclerView = findViewById(R.id.my_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        getWindow().setBackgroundDrawableResource(R.drawable.materialsback);


        recyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Materials");
        Button favourites = findViewById(R.id.favourite);
        favourites.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              reference.addValueEventListener(new ValueEventListener() {
                                                  @Override
                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                      for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                                          Material material = dataSnapshot1.getValue(Material.class);
                                                          String name;
                                                          name = material.getName();
                                                          why = material.getWhy();
                                                          Where = material.getWhere();
                                                          what = material.getWhat();
                                                          how = material.getHow();
                                                          generalcomment = material.getGeneralComment();


                                                          materials.add(new Material(name, what, Where, why, how, generalcomment));


                                                      }


                                                      adapter = new RecycleAdapter(materials, recyclewhat.this);
                                                      recyclerView.setAdapter(adapter);
                                                  }

                                                  @Override
                                                  public void onCancelled(@NonNull DatabaseError databaseError) {

                                                  }
                                              });
                                          }
                                      });

                EditText search = (EditText) findViewById(R.id.search);

                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        filter(s.toString());

                    }
                });

        


      /*
        =================================================================================================

       Algorithm I wrote to parse the json files add them to my database and then add them to a recyclerview
       =======================================================================================================

       */

                String json;
                try {
                    InputStream is = getAssets().open("materials.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");


                    JSONObject obj = new JSONObject(json);
                    JSONArray m_jArry = obj.getJSONArray("Materials");


                    for (int i = 0; i < m_jArry.length(); i++) {

                        JSONObject jo_inside = m_jArry.getJSONObject(i);
                        String name = jo_inside.getString("name");
                        if (jo_inside.getString("What?").equals("This is an electrical item [WEEE].")) {
                            what = jo_inside.getString("What?");
                            Where = "This should be brought to a WEEE dropoff recycling point or Civic Amenity Site, check the waste services locator on this site. waste services locator";
                            why = "When you recycle anything with a plug or a battery you are helping to reduce the amount of waste going to landfill. Electrical items generally contain hazardous components and therefore need to be disposed of correctly. ";
                            generalcomment = "It is easy to check if an electrical item, toy or game is recyclable if it reached the end of its working life. Simply ask the following questions and if the answer is yes, to any of these, it is recyclable: Does it have a plug? Does it use batteries? Does it need charging? Does it have a picture of a crossed out wheelie bin on it";
                            how = " ";
                        } else {
                            what = jo_inside.getString("What?");


                            if (jo_inside.has("Where?")) {
                                Where = jo_inside.getString("Where?");

                            } else {
                                Where = " ";
                            }
                            if (jo_inside.has("Why?")) {
                                why = jo_inside.getString("Why?");

                            } else {

                                why = " ";
                            }

                            if (jo_inside.has("How?")) {
                                how = jo_inside.getString("How?");
                            } else {
                                how = " ";

                            }
                            if (jo_inside.has("General Comment ")) {
                                generalcomment = jo_inside.getString("General Comment ");
                            } else {
                                generalcomment = " ";

                            }
                        }


                        materials.add(new Material(name, what, Where, why, how, generalcomment));


                        adapter = new RecycleAdapter(materials, recyclewhat.this);
                        recyclerView.setAdapter(adapter);


                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            private void filter(String text) {
                ArrayList<Material> filteredList = new ArrayList<Material>();

                for (Material item : materials) {
                    if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                        filteredList.add(item);

                    }

                }
                adapter.filterlist(filteredList);
            }


            protected void onStart() {
                super.onStart();
                Intent intent = getIntent();
            }

    }

