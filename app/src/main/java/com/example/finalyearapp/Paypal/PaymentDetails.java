package com.example.finalyearapp.Paypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearapp.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView txtid, amount, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtid = findViewById(R.id.textid);
        amount = findViewById(R.id.Amount);
        status = findViewById(R.id.Status);

        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("paymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));


        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    private void showDetails(JSONObject response, String paymentAmount){
        try {
            txtid.setText(response.getString("id"));
            status.setText(response.getString("state"));
            amount.setText(response.getString(String.format("$%s",paymentAmount)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
