package com.example.finalyearapp.News;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;



public class ArticlesActivity extends AppCompatActivity {
    private static final String TAG = ArticlesActivity.class.getSimpleName();
    private ListView mListView;
    private ProgressBar mProgressBar;
    private ArticlesViewAdapter mListAdapter;
    private ArrayList<ArticlesItem> mListData;
    private String FEED_URL;
    String title ;
    String image ;
    String description ;
    String Company ;
    String date ;
    String url;

    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    String userid = mFirebaseAuth.getCurrentUser().getUid();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("NewsFavourites").child(userid);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        mListView = (ListView) findViewById(R.id.listView);


        //Initialize with empty data
        mListData = new ArrayList<>();
        mListAdapter = new ArticlesViewAdapter(this, R.layout.article_item, mListData);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArticlesItem item =(ArticlesItem) parent.getItemAtPosition(position);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));

                startActivity(browserIntent);
            }
        });


        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        setTitle(name);

        Intent intent = getIntent();
        String domain = intent.getStringExtra("name");

        if(domain.equals("latest")){
            FEED_URL = "https://newsapi.org/v2/everything?q=climate change&apiKey=e31fb6b25b2d4051ac0b1b62f49d2d69";
            new ArticlesActivity.AsyncHttpTask().execute(FEED_URL);


        }else if(domain.equals("favourites")) {

            Favourites();

        }
        else {



            FEED_URL = "https://newsapi.org/v2/everything?q=climate change&sources=" + domain + "&apiKey=e31fb6b25b2d4051ac0b1b62f49d2d69";
            new ArticlesActivity.AsyncHttpTask().execute(FEED_URL);



        }


        //Start download

    }

    //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpsURLConnection) url.openConnection();


                if (result != null) {

                    String response = streamToString(urlConnection.getInputStream());


                    parseResult(response);



                    return result;



                }
            } catch (MalformedURLException e) {
                e.printStackTrace();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            // Download complete. Let us update UI
            if (result != null) {

                mListAdapter.setListData(mListData);

            } else {
                Toast.makeText(ArticlesActivity.this, "Failed to load data!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        // Close stream
        if (null != stream) {
            stream.close();
        }
        return result;
    }

    /**
     * Parsing the feed results and get the list
     * @param result
     */
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("articles");
            ArticlesItem item;
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                title = post.optString("title");
                image = post.optString("urlToImage");
                description = post.optString("description");
                Company = post.optString("name");
                date = post.optString("publishedAt");
                url = post.optString("url");
                item = new ArticlesItem();
                item.setTitle(title);
                item.setImage(image);
                item.setUrl(url);
                item.setDescription(description);
                item.setDate(date);
                item.setName(Company);

                mListData.add(item);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Favourites(){

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArticlesItem itemfav;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ArticlesItem articlesItem = dataSnapshot1.getValue(ArticlesItem.class);

                    itemfav = new ArticlesItem();
                    itemfav.setTitle(articlesItem.getTitle());
                    itemfav.setImage(articlesItem.getImage());
                    itemfav.setUrl(articlesItem.getUrl());
                    itemfav.setDescription(articlesItem.getDescription());
                    itemfav.setDate(articlesItem.getDate());
                    itemfav.setName(articlesItem.getName());

                    mListData.add(itemfav);

                    Toast.makeText(ArticlesActivity.this, "title" + articlesItem.getTitle(), Toast.LENGTH_LONG).show();
                    Toast.makeText(ArticlesActivity.this, "title" + articlesItem.getDate(), Toast.LENGTH_LONG).show();
                    Toast.makeText(ArticlesActivity.this, "title" + articlesItem.getDescription(), Toast.LENGTH_LONG).show();
                    Toast.makeText(ArticlesActivity.this, "title" + articlesItem.getUrl(), Toast.LENGTH_LONG).show();


                }



                mListView.setAdapter(mListAdapter);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
