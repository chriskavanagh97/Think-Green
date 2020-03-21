package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalyearapp.R;

public class transport extends AppCompatActivity {

    TextView tip1, tip2, tip3, description, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        tip1 = findViewById(R.id.tip1);
        tip2 = findViewById(R.id.tip2);
        tip3 = findViewById(R.id.tip3);

        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        Intent i = getIntent();
        String result = i.getStringExtra("result");
        if (result.equals("transport"))
        {
            transport();
        }
        else if(result.equals("home"))
        {
            home();


        }
        else if(result.equals("purchasing"))
        {

            Stuff();


        }

        else if(result.equals("food"))
        {
            food();


        }






    }

    public void food(){

        tip1.setTextColor(Color.YELLOW);
        description.setText("Everything you buy has a footprint, so try to choose products that have been sustainably sourced. Buying seasonally produced food supports your local agricultural economy but it also helps the environment by cutting down on the packaging, transport and high intensity farming process needed for out of season foods.\n" +
                "\n" +
                "Seasonal food is also cheaper.");
        category.setText("EAT “IN SEASON”");

        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                description.setText("One of the most important changes you can make to the environment is changing your diet. The production of meat and dairy products is one of the major causes of greenhouse gas emissions. So why not cut out meat from your diet and have a more carbon friendly dinner.\n" +
                        "\n");
                category.setText("EAT LESS MEAT AND DAIRY");
            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setHintTextColor(Color.YELLOW);
                description.setText("The way a food is farmed is a hugely important part of how sustainable that type of food is. Constant farming of the same crop types will drain nutrients from the soil. Then farming this crop all year long will give no time for the soil to recover.\n" +
                        "\n" +
                        "By having a colourful plate, we will be ensuring a more nutritious, natural, flavourful and exciting meal – one that is in sync with your ecosystem.");
                category.setText("ADD SOME VARIETY");
            }
        });

    }
    public void Stuff(){
        tip1.setTextColor(Color.YELLOW);
        description.setText("We live in a culture of disposable fashion which is taking its toll on the environment. Buy one well-made expensive item of clothing rather than constantly replacing it with cheaper alternatives. This will stop the waste of resources in manufacturing, it also cuts down the transport costs and carbon cost of the supply chain too. Less clothes being transported means less emissions and trucks on the road.\n" +
                "\n" +
                "Now you have an excuse to treat yourself to that nice coat.\n" +
                "\n");
        category.setText("BUY ONE EXPENSIVE THING");

        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                description.setText("Instead of buying new items such as household items or even clothes why not try your hand at fixing them.\n" +
                        "\n" +
                        "You will save money by doing this and further upskill, if this is too much work find a local store who fill repair tem for you");
                category.setText("REPAIR");
            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setHintTextColor(Color.YELLOW);
                description.setText("A shift to more sustainable shopping practices requires us to appreciate what we already have. Shopping second hand is an excellent way to act with our wallets and change the economy for the better. Charity and second-hand shops have excellent product control procedures, which means the clothes within them is of the highest quality.\n" +
                        "\n" +
                        "You can get the clothes you want for a fraction of the cost – both economic and carbon costs.");
                category.setText("BUY SECOND HAND");
            }
        });

    }
    public void home(){

        tip1.setTextColor(Color.YELLOW);
        description.setText("Changing your energy provider is an excellent way to influence your environmental impact on a national scale. Renewable Energy companies have been driving changes in the energy market and are now providing cheap and sustainable energy. By choosing one of these providers, you are limiting your own footprint, and supporting our shared future.");
        category.setText("SWITCH ENERGY PROVIDERS");

        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                description.setText("Energy efficient bulbs can last up to 25 times longer than traditional bulbs, they also use 80% less power to provide the same light.\n" +
                        "\n" +
                        "They are a better option in terms of product cost, energy cost and will limit the amount of time you waste changing bulbs. Making the switch really is a bright idea.");
                category.setText("MAKE THE SWITCH");
            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setHintTextColor(Color.YELLOW);
                description.setText("Your home can now be controlled by the touch of a button, one usually found on your phone. While this may sound like a gimmick, it is a great way to monitor your energy usage and adapt to a more environmentally friendly way of living. Why power and heat rooms no-one is in? This SMART technology can help limit the impact your home is having on our shared home.");
                category.setText("EMBRACE NEW TECHNOLOGY");
            }
        });


    }
    public void transport(){

        tip1.setTextColor(Color.YELLOW);
        description.setText("The most eco-friendly mode of transport is cycling, it creates no emission, uses no resources and keeps you fit and healthy. And if all that doesn’t sway your opinion - you will save money while helping save the environment.\n" +
                "\n" +
                "By cycling more, you are also taking cars off the road that would otherwise cause congestion and pollution.");
        category.setText("CYCLE");

        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                description.setText("If you have to use your car, then use it properly. There are some bad habits people have that make the impacts of cars a lot worse. Smarter driving can help limit some of the emissions of car usage.\n" +
                        "\n" +
                        "Removing excess weight from the vehicle will also help improve fuel efficiency.\n" +
                        "Having the correct air pressure in your tyres results in better petrol mileage, better handling of the car, cheaper maintenance costs and a smaller environmental impact. This simple step can make a big difference.\n" +
                        "By slowing your travel speed by 10km/h, you could improve your car’s fuel consumption by 25%.");
                category.setText("DRIVE SMARTER");
            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setHintTextColor(Color.YELLOW);
                description.setText("Public transport is a great alternative to driving and will reduce your carbon footprint dramatically.\n" +
                        "\n" +
                        "By taking the train or bus, it means fewer cars on the road overall, resulting in less carbon dioxide being produced. And the cars that remain would have quicker journeys due to decreased congestion on the roads, again resulting in a CO2 reduction.\n" +
                        "\n" +
                        "It will also give you added time to read or listen to music on your journey");
                category.setText("USE PUBLIC TRANSPORT");
            }
        });

    }
}
