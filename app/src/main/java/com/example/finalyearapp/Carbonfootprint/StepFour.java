package com.example.finalyearapp.Carbonfootprint;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.R;



public class StepFour extends AppCompatActivity {
    int i = 1;
    Spinner mySpinner;
    TextView text;

    String housesize;
    int population;
    int bedrooms;
    String heating;
    int degrees;

    String kwh;
    int yearlyvalue;

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_four);

        text = findViewById(R.id.textView);
        mySpinner = (Spinner) findViewById(R.id.values);
        Calculator();





    }

    public void Calculator()
    {


        if(i >5) {

            Calculation();

        }
        else {

            if(i == 1)
            {

                String message = getString(R.string.homeq1);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepFour.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.home1));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if(i == 2)
            {
                String message = getString(R.string.homeq2);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepFour.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.home2));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }

            if(i == 3)
            {
                String message = getString(R.string.homeq3);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepFour.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.home3));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if(i == 4)
            {
                String message = getString(R.string.homeq4);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepFour.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.home4));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if(i == 5)
            {
                String message = getString(R.string.homeq5);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepFour.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.home4));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }

            Button submit = (Button) findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(i == 1 & mySpinner.getSelectedItem().equals("detatched")){

                        housesize = "detatched";
                        Calculator();

                    }
/*
===========================================================================================================
QUESTION TWO ANSWERS
===========================================================================================================
 */

                    else if(i == 1 & mySpinner.getSelectedItem().equals("semi detatched"))
                    {

                        housesize = "semi detatched";
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 1 & mySpinner.getSelectedItem().equals("terrace")) {

                        housesize = "terrace";
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 1 & mySpinner.getSelectedItem().equals("flat")) {

                        housesize = "flat";
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 2 & mySpinner.getSelectedItem().equals("1")) {

                        bedrooms = 1;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 2 & mySpinner.getSelectedItem().equals("2")) {

                        bedrooms = 2;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 2 & mySpinner.getSelectedItem().equals("3")) {

                        bedrooms = 3;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 2 & mySpinner.getSelectedItem().equals("4 or more")) {

                        bedrooms = 4;
                        i = i + 1;
                        Calculator();
                    }


/*
===========================================================================================================
QUESTION THREE ANSWERS
===========================================================================================================
 */

                    else if(i == 3 & mySpinner.getSelectedItem().equals("1")) {

                        population = 1;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 3 & mySpinner.getSelectedItem().equals("2")) {

                        population = 2;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 3 & mySpinner.getSelectedItem().equals("3")) {

                        population = 3;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 3 & mySpinner.getSelectedItem().equals("4")) {

                        population = 4;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 3 & mySpinner.getSelectedItem().equals("5 or more")) {

                        population = 5;
                        i = i + 1;
                        Calculator();
                    }
/*
===========================================================================================================
QUESTION FOUR ANSWERS
===========================================================================================================
 */

                    else if(i == 4 & mySpinner.getSelectedItem().equals("electricity")) {

                        heating = "electricity";
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 4 & mySpinner.getSelectedItem().equals("wood")) {

                        heating = "wood";
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 4 & mySpinner.getSelectedItem().equals("oil")) {

                        heating = "oil";
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 4 & mySpinner.getSelectedItem().equals("gass")) {

                        heating = "gass";
                        i = i + 1;
                        Calculator();
                    }


/*
===========================================================================================================
QUESTION FIVE ANSWERS
===========================================================================================================
 */


                    else if(i == 5 & mySpinner.getSelectedItem().equals("below 14 degrees")) {

                        degrees = 10;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 5 & mySpinner.getSelectedItem().equals("14 to 17 degrees")) {

                        degrees = 15;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 5 & mySpinner.getSelectedItem().equals("18 to 21 degrees")) {

                        degrees = 20;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 5 & mySpinner.getSelectedItem().equals("over 21 degrees")) {

                        degrees = 25;
                        i = i + 1;
                        Calculator();
                    }



                    else {

                        i = i + 1;
                        Calculator();

                    }

                }
            });



        }
    }
    public void Calculation(){




        if(population <= 2)
        {
            kwh = "low";
            kwh();
        }
        else if(population == 3 & housesize.equals("terrace") || population == 3 & housesize.equals("flat"))
        {
            kwh = "low";
            kwh();
        }
        else if(population == 3  & housesize.equals("semi detatched")|| population == 3  & housesize.equals("detatched"))
        {
            kwh = "medium";
            kwh();
        }
        else if(population == 4 & bedrooms <=3  & housesize.equals("flat") || population == 4 & bedrooms <=3  & housesize.equals("terrace") || population == 4 & bedrooms <=3  & housesize.equals("semi detatched")){

            kwh = "medium";
        } else if(population == 4 & bedrooms > 3){

            kwh = "high";
            kwh();
        }
        else if(population > 4  ){

            kwh = "high";
            kwh();
        }

    }

    public void kwh(){



        if (kwh.equals("low") &  heating.equals("electricity") || kwh.equals("low") &  heating.equals("wood")) {

            yearlyvalue = 609;



            parsing();


        }
        else if (kwh.equals("medium") &  heating.equals("electricity") || kwh.equals("medium") &  heating.equals("wood")) {

            yearlyvalue = 802;

            parsing();


        }
        else if (kwh.equals("high") &  heating.equals("electricity") || kwh.equals("high") &  heating.equals("wood")) {

            yearlyvalue = 1134;

            parsing();


        }
        else if (kwh.equals("low") &  heating.equals("gass") || kwh.equals("low") &  heating.equals("oil")) {

            yearlyvalue = 415;

            parsing();


        }
        else if (kwh.equals("medium") &  heating.equals("gass") || kwh.equals("medium") &  heating.equals("oil")) {

            yearlyvalue = 662;

            parsing();


        }
        else if (kwh.equals("high") &  heating.equals("gass") || kwh.equals("high") &  heating.equals("oil")) {

            yearlyvalue = 824;

            parsing();


        }
    }
    public void parsing()
    {
        Intent resultintent = new Intent(StepFour.this, Results.class);
        startActivity(resultintent);


        Toast.makeText(StepFour.this, "parsing groups", Toast.LENGTH_SHORT).show();
        Intent i = getIntent();

        String mpg = i.getStringExtra("mpg");
        String miles = i.getStringExtra("miles");
        String bushours = i.getStringExtra("train");
        String trainhours = i.getStringExtra("bus");

        String beauty = i.getStringExtra("beauty");
        String entertainment = i.getStringExtra("clothes");
        String household = i.getStringExtra("entertainment");
        String clothes = i.getStringExtra("house");

        String url = "https://apis.berkeley.edu/coolclimate/footprint-sandbox?input_size=" + population +
                "&input_footprint_transportation_fuel1=1"+
                "&input_footprint_transportation_miles1="+miles+
                "&input_footprint_transportation_mpg1="+ mpg +
                "&input_footprint_transportation_bus="+ bushours +
                "&input_footprint_transportation_commuter=" + trainhours +
                "&input_footprint_shopping_goods_default_furnitureappliances=" + household +
                "&input_footprint_shopping_goods_default_clothing=" + clothes +
                "&input_footprint_shopping_goods_default_other_personalcare=" + beauty +
                "&input_footprint_shopping_goods_default_other_entertainment=" + entertainment +
                "&input_footprint_housing_electricity_dollars="+yearlyvalue+
                "&input_footprint_housing_naturalgas_dollars="+ yearlyvalue;
               // " -H \"accept: application/json\" -H \"app_id: dae89a90\" -H \"app_key: 88000d3ffd63f5312b37f888b9c2c792";

                    HandleXml obj;
                   TextView text1 = findViewById(R.id.transport);
                   TextView text2 = findViewById(R.id.home);
                   TextView text3 = findViewById(R.id.purchasing);
                   TextView text4 = findViewById(R.id.food);
                   TextView text5 = findViewById(R.id.total);

                   obj = new HandleXml(url);
                   obj.fetchXML();

                   while (obj.parsingComplete);
                   text1.setText(obj.getTransport());
                   text2.setText(obj.getHome());
                   text3.setText(obj.getPurchasing());
                   text4.setText(obj.getFood());
                   text5.setText(obj.getTotal());





    }


}
