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
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalyearapp.R;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        setTitle(name);

        Intent intent = getIntent();
        String domain = intent.getStringExtra("name");

        FEED_URL = "https://newsapi.org/v2/everything?q=climate crisis&sources=" + domain + "&apiKey=e31fb6b25b2d4051ac0b1b62f49d2d69";
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


        //Start download
        new ArticlesActivity.AsyncHttpTask().execute(FEED_URL);

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
                String title = post.optString("title");
                String image = post.optString("urlToImage");
                String description = post.optString("description");
                String Company = post.optString("name");
                String date = post.optString("publishedAt");
                String url = post.optString("url");
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

}
