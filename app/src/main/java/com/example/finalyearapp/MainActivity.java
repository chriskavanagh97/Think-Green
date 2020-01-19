package com.example.finalyearapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearapp.RecycleMaterial.Material;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText EmailId;
    private EditText Password;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public static final String KEY1="com.example.MESSAGE1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        EmailId = findViewById(R.id.EmailLogin);
        Password = findViewById(R.id.LoginPassword);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            //=========================================================================================================================================================================================
            //If somebody opens the app and are already logged in this will check it and send them to the menu
            //=========================================================================================================================================================================================

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null){
                    Toast.makeText(MainActivity.this,"You are Logged in",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,
                            MainMenu.class);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(MainActivity.this,"You are Logged out",Toast.LENGTH_SHORT).show();

                }

            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        //===========
        // ==============================================================================================================================================================================
        //Setting up an onclick listener for my Login
        //
        //When Login is clicked it will check if each box contains a value and then check if the values are correct
        //=========================================================================================================================================================================================

        Button login = (Button) findViewById(R.id.LoginButton);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View c) {
                String Email = EmailId.getText().toString().trim();
                String pword = Password.getText().toString().trim();
                if (Email.isEmpty()) {
                    EmailId.setError("Please enter Email");
                    EmailId.requestFocus();
                } else if (pword.isEmpty()) {
                    Password.setError("Please enter Password");
                    Password.requestFocus();
                } else {


                    //=========================================================================================================================================================================================
                    //Here we then sign in the user with the Firebase Authenticaiton
                    //=========================================================================================================================================================================================

                    mFirebaseAuth.signInWithEmailAndPassword(Email, pword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                startActivity(new Intent(MainActivity.this, MainMenu.class));

                            }
                            else {

                                Toast.makeText(MainActivity.this, "Login Error, Please Login Again!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }

            }
        });


        //=========================================================================================================================================================================================
        //Creating a register intent so if people haven't signed up that they can register
        //=========================================================================================================================================================================================

        TextView registerButton = (TextView) findViewById(R.id.Register);
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
        public void onClick(View v){
            Intent intent=new Intent(MainActivity.this,
                    Register.class);

            startActivity(intent);
        }


    });
    }
    //=========================================================================================================================================================================================
    //Hardcoding questions into my database
    //=========================================================================================================================================================================================

    protected void onStart() {
        super.onStart();
       mFirebaseAuth.addAuthStateListener((mAuthStateListener));

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("Questions");

        DatabaseReference mRootRef1 = FirebaseDatabase.getInstance().getReference().child("Materials");
        Question newQuestion = new Question();

        newQuestion.setQuestion("How many tonnes of plastic is dumped in the ocean each year");
        newQuestion.setAnswer1("750,000 ");
        newQuestion.setAnswer2("200,000 ");
        newQuestion.setDifficulty("Easy");
        newQuestion.setAnswer3("10,000,000 ");
        newQuestion.setAnswerCorrect("8,000,000 ");
        newQuestion.setCategory("Plastic");
        newQuestion.setDescription("We are facing a crisis, there is too much plastic in the ocean due to illegal dumping. Recycle safely or Stop using plastics");
        newQuestion.setScore(100);
        newQuestion.setId(1);

        mRootRef.child("Plastic").child("1").setValue(newQuestion);


        Question newQuestion1 = new Question();

        newQuestion1.setQuestion("What type of supermarket bag is more ecofriendly, paper or plastic?");
        newQuestion1.setAnswer1("Plastic");
        newQuestion1.setAnswer2("Paper");
        newQuestion1.setDifficulty("Easy");
        newQuestion1.setAnswer3("Either");
        newQuestion1.setAnswerCorrect("Neither");
        newQuestion1.setDescription("Manufacturing and disposing of paper and plastic bags is bad for the environment use your own reusable bags ");
        newQuestion1.setScore(100);
        newQuestion1.setId(2);

        mRootRef.child("Plastic").child("2").setValue(newQuestion1);

        Question newQuestion2 = new Question();

        newQuestion2.setQuestion("Approximately how much global electricity output is produced from renewable sources?");
        newQuestion2.setAnswer1("25%");
        newQuestion2.setAnswer2("11%");
        newQuestion2.setDifficulty("Easy");
        newQuestion2.setAnswer3("2%");
        newQuestion2.setAnswerCorrect("10%");
        newQuestion2.setDescription("Only 10% of energy comes from renewable sources, the other 90 comes from oil, gas and coals etc");
        newQuestion2.setScore(100);
        newQuestion2.setId(3);


        mRootRef.child("Plastic").child("3").setValue(newQuestion2);

        Question newQuestion3 = new Question();

        newQuestion3.setQuestion("Which of the following is an alternative material for making paper?");
        newQuestion3.setAnswer1("hemp");
        newQuestion3.setAnswer2("sheep skin");
        newQuestion3.setDifficulty("Easy");
        newQuestion3.setAnswer3("Panda Excrement");
        newQuestion3.setAnswerCorrect("All of the above");
        newQuestion3.setDescription("All of these can be used although hemp is the cleanest out of them all and it easily recycled ");
        newQuestion3.setScore(100);
        newQuestion3.setId(4);


        mRootRef.child("Plastic").child("4").setValue(newQuestion3);

        Question newQuestion4 = new Question();

        newQuestion4.setQuestion("How much single use plastic bottles are thrown out each hour in the US");
        newQuestion4.setAnswer1("1,000");
        newQuestion4.setAnswer2("12,500");
        newQuestion4.setDifficulty("Easy");
        newQuestion4.setAnswer3("20,000");
        newQuestion4.setAnswerCorrect("25,0000");
        newQuestion4.setDescription("majority of it ends up in the ocean through deliberate dumping of garbage into waterways, inefficient waste infrastructure, and simple littering.");
        newQuestion4.setScore(100);
        newQuestion4.setId(5);


        mRootRef.child("Plastic").child("5").setValue(newQuestion4);
        Question newQuestion5 = new Question();

        newQuestion5.setQuestion("What happens to plastic");
        newQuestion5.setAnswer1("It is biodegradable material os it eventually disintegrates");
        newQuestion5.setAnswer2("It is dumped into the ocean for the fish to eat");
        newQuestion5.setDifficulty("Easy");
        newQuestion5.setAnswer3("There is no such thing as plastic waste, all plastic is recycled");
        newQuestion5.setAnswerCorrect("It never fully goes away, it just breaks into little pieces");
        newQuestion5.setDescription("Plastic will never ever disappear, it will continually break down into smaller pieces, it highlights the importance of reducing plastic usage ");
        newQuestion5.setScore(100);
        mRootRef.child("Plastic").child("6").setValue(newQuestion5);
        newQuestion5.setId(6);

        Question newQuestion6 = new Question();


        newQuestion6.setQuestion("Why are plastic bags dangerous for marine life");
        newQuestion6.setAnswer1("They mistake it for food and cannot digest it");
        newQuestion6.setAnswer2("It is not dangerous as they use it to build their habitats");
        newQuestion6.setDifficulty("Easy");
        newQuestion6.setAnswer3("They get tangled in it and can reduce their ability to swim");
        newQuestion6.setAnswerCorrect("Both A and c");
        newQuestion6.setDescription("Plastic bags are often mistaken for  jellyfish and consumed by turtles, plastic often floats and bobbles which is why it is mistaken for marine life");
        newQuestion6.setScore(100);
        newQuestion6.setId(7);


        mRootRef.child("Plastic").child("7").setValue(newQuestion6);

        Question newQuestion7 = new Question();


        newQuestion7.setQuestion("How many marine species are harmed by plastic pollution?");
        newQuestion7.setAnswer1("52");
        newQuestion7.setAnswer2("693");
        newQuestion7.setDifficulty("Easy");
        newQuestion7.setAnswer3("1,326");
        newQuestion7.setAnswerCorrect("693");
        newQuestion7.setDescription("A 2015 Plymouth University study compiled reports recorded from around the world and found evidence of 44,000 animals becoming entangled or swallowing plastic debris");
        newQuestion7.setScore(100);
        newQuestion7.setId(8);


        mRootRef.child("Plastic").child("8").setValue(newQuestion7);


        Question newQuestion8 = new Question();


        newQuestion8.setQuestion("What percent of its plastic does the US recycle?");
        newQuestion8.setAnswer1("70%");
        newQuestion8.setAnswer2("35%");
        newQuestion8.setDifficulty("Easy");
        newQuestion8.setAnswer3("50%");
        newQuestion8.setAnswerCorrect("9%");
        newQuestion8.setDescription("if current production and management trends continue in the U.S., 12 billion Mt of plastic waste will end up in landfills or the ocean by 2050.");
        newQuestion8.setScore(100);
        newQuestion8.setId(9);


        mRootRef.child("Plastic").child("9").setValue(newQuestion8);

        Question newQuestion9 = new Question();

        mRootRef.child("Plastic").child("6").setValue(newQuestion5);
        newQuestion9.setQuestion("Approximately, Americans use about how many plastic drinking straws per day");
        newQuestion9.setAnswer1("25,000");
        newQuestion9.setAnswer2("250,000,000");
        newQuestion9.setDifficulty("Easy");
        newQuestion9.setAnswer3("100,000");
        newQuestion9.setAnswerCorrect("500,000,000");
        newQuestion9.setDescription("On average, Americans use 1.6 plastic drinking straws every day, totaling to 500 million per day. That’s enough to fill up 125 school buses a day, or 46,400 school buses a year! ");
        newQuestion9.setScore(100);
        newQuestion9.setId(10);


        mRootRef.child("Plastic").child("10").setValue(newQuestion9);

        Question pollutionquiz = new Question();


        pollutionquiz.setQuestion("How many deaths are there annually as a result of exposure to outdoor air pollution?");
        pollutionquiz.setAnswer1("100");
        pollutionquiz.setAnswer2("200,000");
        pollutionquiz.setDifficulty("Easy");
        pollutionquiz.setAnswer3("2,000,000");
        pollutionquiz.setAnswerCorrect("3,700,000");
        pollutionquiz.setDescription("According to statistics from the UN, 3.7 million deaths are attributed to outdoor air pollution. ");
        pollutionquiz.setScore(100);
        pollutionquiz.setId(1);


        mRootRef.child("Pollution").child("1").setValue(pollutionquiz);

        Question pollutionquiz1 = new Question();


        pollutionquiz1.setQuestion("Facemasks are often used in China during air pollution episodes. What is the percentage range of ambient air particles that are filtered by standard facemasks?");
        pollutionquiz1.setAnswer1("90 - 100%");
        pollutionquiz1.setAnswer2("1- 15%");
        pollutionquiz1.setDifficulty("Easy");
        pollutionquiz1.setAnswer3("26 - 40%");
        pollutionquiz1.setAnswerCorrect("48 - 75%");
        pollutionquiz1.setDescription("A study in Particle and Fibre Toxicology tested 6 types of standard facemasks used in China and found that the most effective facemask removed only 75% of ambient air particles and the least effective mask removed 48%.");
        pollutionquiz1.setScore(100);
        pollutionquiz1.setId(2);


        mRootRef.child("Pollution").child("2").setValue(pollutionquiz1);

        Question pollutionquiz2 = new Question();


        pollutionquiz2.setQuestion("Does Environmental pollution exposure during pregnancy increase risk factor for pre-term (premature) birth");
        pollutionquiz2.setAnswer1("No only smoking causes this");
        pollutionquiz2.setAnswer2("no the air you breathin doesnt effect your baby");
        pollutionquiz2.setDifficulty("Easy");
        pollutionquiz2.setAnswer3("only if you work in polluted areas");
        pollutionquiz2.setAnswerCorrect("yes regular exposure to pollution increase chances of pre term");
        pollutionquiz2.setDescription("A study in Particle and Fibre Toxicology tested 6 types of standard facemasks used in China and found that the most effective facemask removed only 75% of ambient air particles and the least effective mask removed 48%.");
        pollutionquiz2.setScore(100);
        pollutionquiz2.setId(3);


        mRootRef.child("Pollution").child("3").setValue(pollutionquiz2);

        Question pollutionquiz3 = new Question();
        pollutionquiz3.setQuestion("Exposure to indoor second hand smoke, dirty cooking fuel, and dampness increases the risk of which infectious disease in children?");
        pollutionquiz3.setAnswer1("Malaria");
        pollutionquiz3.setAnswer2("Mumps");
        pollutionquiz3.setDifficulty("Cholera");
        pollutionquiz3.setAnswer3("only if you work in polluted areas");
        pollutionquiz3.setAnswerCorrect("Tuberculosis");
        pollutionquiz3.setDescription("A study based in Durban, South Africa published in BMC Public Health suggests an increased risk of childhood tuberculosis when exposed to these types of indoor air pollutants.");
        pollutionquiz3.setScore(100);
        pollutionquiz3.setId(4);

        mRootRef.child("Pollution").child("4").setValue(pollutionquiz3);
        Question pollutionquiz4 = new Question();
        pollutionquiz4.setQuestion("How much CO2 was removed from the atmosphere by Brazilian forest plantations between 1990 and 2016?");
        pollutionquiz4.setAnswer1("1,000 tonnes");
        pollutionquiz4.setAnswer2("10 gigatonnes");
        pollutionquiz4.setDifficulty("100 gigatones");
        pollutionquiz4.setAnswer3("only if you work in polluted areas");
        pollutionquiz4.setAnswerCorrect("1669 Gigatonnes");
        pollutionquiz4.setDescription("1669 Gigatonnes of CO2 over the 26 year period. 1 Gigatonne is equal to 1 billion tonnes. This study emphasizes the important role forests play in mitigating greenhouse gas emissions");
        pollutionquiz4.setScore(100);
        pollutionquiz4.setId(5);

        mRootRef.child("Pollution").child("5").setValue(pollutionquiz4);



        Question pollutionquiz5 = new Question();
        pollutionquiz5.setQuestion("Which of the following is the most effective strategy to reduce the impact of toxic waste");
        pollutionquiz5.setAnswer1("reusing raw materials");
        pollutionquiz5.setAnswer2("raising taxes on companies that pollute");
        pollutionquiz5.setDifficulty("recycling polluting materials");
        pollutionquiz5.setAnswer3("only if you work in polluted areas");
        pollutionquiz5.setAnswerCorrect("stopping the production of polluting materials");
        pollutionquiz5.setDescription("Toxins can be reduced through the substitution of nonpolluting alternatives, such as oxygen for chlorine in the bleaching of wood, or through green chemistry");
        pollutionquiz5.setScore(100);
        pollutionquiz5.setId(6);

        mRootRef.child("Pollution").child("6").setValue(pollutionquiz5);

        Question ocean = new Question();
        ocean.setQuestion("Other than melting land-based ice sheets, which of these factors has made the largest contribution to the rise in sea level over the past 100 years?");
        ocean.setAnswer1("Climate change including Rain");
        ocean.setAnswer2("melting sea ice");
        ocean.setDifficulty("easy");
        ocean.setAnswer3("increased river run off");
        ocean.setAnswerCorrect("warming of ocean surface waters");
        ocean.setDescription("As the ocean warms, it expands and sea level rises, accounting for about a third of the approximately 20-centimeter sea level rise seen in the past century. Water released by melting land-based ice sheets contributes the other two-thirds of sea level rise");
        ocean.setScore(100);
        ocean.setId(1);

        mRootRef.child("Ocean").child("1").setValue(ocean);

        Question ocean2 = new Question();
        ocean2.setQuestion("What percentage of heat from global warming has the ocean absorbed in the past 40 years?");
        ocean2.setAnswer1("20%");
        ocean2.setAnswer2("66%");
        ocean2.setDifficulty("easy");
        ocean2.setAnswer3("5%");
        ocean2.setAnswerCorrect("93%");
        ocean2.setDescription("Water resists changes in temperature; it is slow to heat up and slow to cool down. In scientific terms, water has high heat capacity. This means that, so far, Earth's ocean has been able to absorb and hold a majority of the heat from Earth's atmosphere");
        ocean2.setScore(100);
        ocean2.setId(2);

        mRootRef.child("Ocean").child("2").setValue(ocean2);


        Question ocean3 = new Question();
        ocean3.setQuestion("Melting sea ice has the potential to raise sea level by how many metres");
        ocean3.setAnswer1("15");
        ocean3.setAnswer2("30");
        ocean3.setDifficulty("easy");
        ocean3.setAnswer3("45");
        ocean3.setAnswerCorrect("0");
        ocean3.setDescription("Melting sea ice cannot raise global sea level since the ice is already floating. (Think of an ice cube melting in a glass full of water.) However, Arctic sea ice is thinning and the long-term summer average has decreased by 34 percent since 1979. Ice from glaciers and ice sheets, which form on land, does add water to Earth's ocean when it melts and does contribute to sea level rise.");
        ocean3.setScore(100);
        ocean3.setId(3);

        mRootRef.child("Ocean").child("3").setValue(ocean3);


        Question ocean4 = new Question();
        ocean4.setQuestion("What percentage of the world's population lives within 100 kilometers of the shoreline?");
        ocean4.setAnswer1("10");
        ocean4.setAnswer2("80%");
        ocean4.setDifficulty("easy");
        ocean4.setAnswer3("25%");
        ocean4.setAnswerCorrect("39%");
        ocean4.setDescription("According to the World Resources Institute, in 1995 2.2 billion people, or 39 percent of the world's population, lived on or within 100 kilometers of a seashore. Recent studies reveal that up to 600 million people live in Low Elevation Coastal Zones and 200 million people live within coastal flood plains. ");
        ocean4.setScore(100);
        ocean4.setId(4);

        mRootRef.child("Ocean").child("4").setValue(ocean4);

        Question ocean5 = new Question();
        ocean5.setQuestion("Sea level rise contributes to more frequent flooding in which of these coastal areas?");
        ocean5.setAnswer1("Venice");
        ocean5.setAnswer2("Bangladesh");
        ocean5.setDifficulty("easy");
        ocean5.setAnswer3("");
        ocean5.setAnswerCorrect("39%");
        ocean5.setDescription("According to the World Resources Institute, in 1995 2.2 billion people, or 39 percent of the world's population, lived on or within 100 kilometers of a seashore. Recent studies reveal that up to 600 million people live in Low Elevation Coastal Zones and 200 million people live within coastal flood plains. ");
        ocean5.setScore(100);
        ocean5.setId(5);

        mRootRef.child("Ocean").child("5").setValue(ocean5);



        Material paper = new Material();
        paper.setName("Paper");
        paper.setWhat("This is a paper or card");
        paper.setWhere("This should be placed in the household recycling bin.");
        paper.setHow("Please make sure items are clean, dry and placed loosely in the bin.");
        paper.setTip("If paper or card become wet or dirty on its way to the sorting facility it may not be recycled.");

    mRootRef1.child("Paper").setValue(paper);



        Material glassbottles = new Material();
        glassbottles.setName("Glass bottles and Jars");
        glassbottles.setWhat("Glass bottles and jars generally fall into three categories – clear glass, green glass and brown glass, each of which is recyclable.");
        glassbottles.setWhere("All clear glass, green and brown glass should be placed into glass recycling containers at your local bring bank.");
        glassbottles.setHow("Find your local facilities please check our waste services locator");
        glassbottles.setTip("Please make sure all bottles are clean and empty before recycling");
    mRootRef1.child("Glass bottles and Jars").setValue(glassbottles);

        Material aerosols = new Material();
        aerosols.setName("Aerosols");
        aerosols.setWhat("Aerosols are generally pressurised cans like deodorant, air freshener or bug sprays.");
        aerosols.setWhere("These should be brought to your local civic amenity site and if this is not an option please place them in your general waste bin.");
        aerosols.setHow("Find your local facilities please check our waste services locator");
        aerosols.setTip("In Ireland, as per 2nd Schedule category 1 waste of The Waste Management Act, 1996,  aerosols fall into the category of hazardous waste and therefore cannot be placed in the household recycle bin. ");
        mRootRef1.child("Aerosols").setValue(aerosols);

        Material medicine = new Material();
        medicine.setName("Medicines");
        medicine.setWhat("This is hazardous waste.");
        medicine.setWhere("Return unused or unwanted medicines to the pharmacy or dispose of at your local civic amenity site if it accepts this type of hazardous waste. Check the waste services locator on this site.");
        medicine.setHow("Find your local facilities please check our waste services locator");
        medicine.setTip("");
    mRootRef1.child("Medicines").setValue(medicine);

        Material battery = new Material();
        battery.setName("Batteries");
        battery.setWhat("Household batteries of any size including battery packs from electrical items are suitable for recycling.");
        battery.setWhere("Batteries should be brought to a WEEE dropoff recycling point or to a Civic Amenity Site.");
        battery.setHow("Find your local facilities please check our waste services locator, When you recycle anything with a plug or a battery you are helping to reduce the amount of waste going to landfill");
        battery.setTip("Most supermarkets will have battery boxes where you can leave your used batteries for free.\n" +
                "If you use a lot of batteries why not try using rechargeable ones instead.");
    mRootRef1.child("Batteries").setValue(battery);


        Material food = new Material();
        food.setName("Food waste");
        food.setWhat("Plate scrapings, food which has spoiled [remove it from any packaging]");
        paper.setWhere("This should be placed in the food waste recycling bin if you are entitled to a collection service.");
        paper.setHow("Simply remove any food from packaging and place in your food recycling bin (brown bin) ");
        paper.setTip("Why not try home composting,");
        mRootRef1.child("Food Waste").setValue(food);

        Material pizzabox = new Material();
        pizzabox.setName("Pizza Box");
        pizzabox.setWhat("This is a paper or card");
        pizzabox.setWhere("This should be placed in the household recycling bin.\n" +
                "If you have a take out pizza this box may be soiled and needs to be treated differently");
        pizzabox.setHow("Please make sure items are clean, dry and placed loosely in the bin. If you have a take out pizza, if the whole box is greasy and dirty please place it in the food waste recycle bin. If the top of the box is clean it can be torn off and placed in the household recycle bin. ");
        pizzabox.setTip("If the pizza box is soiled with grease please put it in the food waste recycling bin along with any uneaten pizza. Clean pizza boxes can be placed in the recycling bin.\n" +
                "Please note that if you have a frozen pizza or one from a fridge that comes in a box this box will be 100% recyclable and should be placed in the recycle bin or why not leave it in the store.");
        mRootRef1.child("Pizza Box").setValue(pizzabox);


        Material beveragecans = new Material();
        beveragecans.setName("Beverage Cans");
        beveragecans.setWhat("Most beverage cans are made of aluminium and a smaller number are made of tin-plated steel");
        beveragecans.setWhere("These should be placed in the household recycling bin but some Civic Amenity Sites and Bring Banks also accept beverage cans check out the Waste Services Locator on this site for further details.");
        beveragecans.setHow("Make sure all beverage cans placed in the household recycling bin are clean and dry and placed loosely in the bin.\n" +
                "You can leave labels on as these are removed through the recycling process.");
        beveragecans.setTip("Look out for charities such as Every Can Counts at events and festivals: http://www.everycancounts.ie");
        mRootRef1.child("Beverage Cans").setValue(beveragecans);


        Material wetwipes = new Material();
        wetwipes.setName("Wet Wipes");
        wetwipes.setWhat("This is a soft plastic");
        wetwipes.setWhere("This should be placed in the general waste bin.");
        wetwipes.setHow("Baby wipes, cosmetic wipes, bathroom cleaning wipes and moist toilet tissues are NOT recyclable and are NON flushable either even though some labels say they are! They should always be placed in your general waste bin.");
        wetwipes.setTip("Why not try alternatives to single use disposable items such as wipes? Can you use a flannel or muslin square instead. These items can be washed and reused over & over.");
        mRootRef1.child("Wet Wipes").setValue(wetwipes);

        Material Jewellery = new Material();
        Jewellery.setName("Jewellery");
        Jewellery.setWhat("ewellery and watches are accepted by many charities.\n" +
                "Broken and outdated jewellery can often be repaired and remodelled to make it fashionable and usable again.");
        Jewellery.setWhere("Donate to charity. Many pieces of jewellery can be recycled or re-sold to raise valuable funds.\n" +
                "Always check the list of  items accepted by the various charities first as they do vary considerably.\n" +
                "Check Ireland’s repair director for jewellers that offer repair and remodelling services: http://www.repairmystuff.ie");
        Jewellery.setHow("Always place jewellery for donation in a separate bag to keep the pieces together – otherwise, they may get lost in the sorting process.");
        Jewellery.setTip("");
        mRootRef1.child("Jewellery").setValue(Jewellery);

        Material Aluminium = new Material();
        Aluminium.setName("Aluminium trays");
        Aluminium.setWhat("Aluminium foil and trays are a type of metal similar to tins & cans.");
        Aluminium.setWhere("Aluminium foil and trays can be placed in the household recycling bin but only if completely clean.");
        Aluminium.setHow("Aluminium can be recycled over and over without losing any of its properties" +
                "All food residue must be removed, then place the foil in the bin clean, dry and loosely.\n" +
                "Scrunch kitchen foil and tubs to form a ball – the bigger the ball, the easier it is to recycle..");
        Aluminium.setTip("");
        mRootRef1.child("Aluminium trays").setValue(Aluminium);

        Material chair = new Material();
        chair.setName("Chair");
        chair.setWhat("This is furniture / a bulky item");
        chair.setWhere("Furniture at the end of its useful life can often be recycled, especially items made from wood and metal. Most furniture can be recycled at your local civic amenity site and some local authorities may also provide a bulky goods collection service for a fee.");
        chair.setHow("");
        chair.setTip("If you have a piece of furniture that is still usable, you could consider selling it, passing it on to someone else or donating it – some charity shops accept furniture for resale.");
        mRootRef1.child("Chair").setValue(chair);

      Material Animalbedding = new Material();
      Animalbedding.setName("Animal bedding & waste");
      Animalbedding.setWhat("Household animal bedding generally consists of old dog and cat beds");
      Animalbedding.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
      Animalbedding.setHow("");
      Animalbedding.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");
      mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Asbestos = new Material();
        Asbestos.setName("Asbestos");
        Asbestos.setWhat("Asbestos is a hazardous waste and must be disposed of safely.");
        Asbestos.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Asbestos.setHow("Asbestos should be removed & disposed of by a specialist hazardous waste contractor \n" + "You can search for specialised contractors on the National Waste Collection Permit site: www.nwcpo.ie.");
        Asbestos.setTip("Asbestos is extremely hazardous.\n" + "If you find asbestos in your home, call an expert!");
        mRootRef1.child("Asbestos").setValue(Asbestos);

        Material Ash = new Material();
        Ash.setName("Ash");
        Ash.setWhat("Ash is produced generally from coal, wood or turf.");
        Ash.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Ash.setHow("");
        Ash.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");
        mRootRef1.child("Ash").setValue(Ash);

        Material Barbecues = new Material();
        Barbecues.setName("Barbecues");
        Barbecues.setWhat("Barbecues are a household bulky item and are generally made of metal.");
        Barbecues.setWhere("Some Civic Amenity Sites will accept metal items.\n" +
                "Check the map for your local civic amenity site on the Waste Services Locator on this site");
        Barbecues.setHow("");
        Barbecues.setTip("Disposable BBQs should be placed in the general waste bins.\n" +
                "Ensure ash is fully cold..");
        mRootRef1.child("Barbecues").setValue(Barbecues);

        Material Bathroom = new Material();
        Bathroom.setName("Bathroom suites");
        Bathroom.setWhat("Bathroom suites no longer fit for purpose can be disposed of similarly to construction and demolition (C&D) waste.");
        Bathroom.setWhere("Skip hire companies provide varying skip services.\n" +
                "Some civic amenity sites also accept this material. Check the waste services locator on this site for further detail.");
        Bathroom.setHow("");
        Bathroom.setTip("Before disposing of bathroom suites always consider reuse.");

        mRootRef1.child("Bathroom suites").setValue(Bathroom);

        Material Bedding = new Material();
        Bedding.setName("Bedding & blankets");
        Bedding.setWhat("Bedding and blankets generally cover duvets, blankets, bed sheets, duvet covers, pillows and throws.");
        Bedding.setWhere("Pillows and duvets due to hygiene reasons (unless new & unused) should be placed in the general waste bin.\n" +
                "Other textiles such as duvet covers or blankets can be recycled in textile banks.");
        Bedding.setHow("");
        Bedding.setTip("If you have NEW unused and unwanted items these can be donated to charity.\n" +
                "If you have used ones which are in good condition perhaps you could donate them to an animal shelter.");
        mRootRef1.child("Bedding & blankets").setValue(Bedding);

        Material Bicycles = new Material();
        Bicycles.setName("Bicycles");
        Bicycles.setWhat("Bicycles are generally made of metal or aluminium which is recyclable..");
        Bicycles.setWhere("Check the waste services locator on this site for recycling options.  There are also bicycle refurbishment projects listed on www.crni.ie");
        Bicycles.setHow("");
        Bicycles.setTip("Even bicycles in poor condition can be repaired, refurbished and re-used. \n" +
                "However if your bicycle is beyond repair the main parts of your old bike can be recycled at your nearest civic amenity site.");
        mRootRef1.child("Bicycles").setValue(Bicycles);

        Material Blacktray = new Material();
        Blacktray.setName("Black Trays");
        Blacktray.setWhat("Rigid trays which any type of meat can be purchased in, they come in many colours and will normally have a soft film covering.");
        Blacktray.setWhere("The rigid plastic tray can be placed in the household recycling bin and the softer plastic cover should go into the general waste bin.");
        Blacktray.setHow("All rigid trays regardless of the colour, number, symbol or lack thereof can be placed in the household recycling bin. Trays should be clean, if there is a soak pad this must be removed [place it in the black or food waste bin], you then need to clean out the tray by carefully washing it ensuring you do not splash any liquid on the counter tops or sink or if you are a bit unsure about washing it in the sink you can simply wipe out any excess liquid with paper towels and then dispose of these in the food waste recycling bin [this will ensure no bacteria gets spread on counters or sink]");
        Blacktray.setTip("");

        mRootRef1.child("Black Tray").setValue(Blacktray);
        Material Blender = new Material();
        Blender.setName("Blender");
        Blender.setWhat("This is an electrical item [WEEE]");
        Blender.setWhere("This should be brought to a WEEE dropoff recycling point or Civic Amenity Site, check the waste services locator in this app.");
        Blender.setHow("When you recycle anything with a plug or a battery you are helping to reduce the amount of waste going to landfill. Electrical items generally contain hazardous components and therefore need to be disposed of correctly. By recycling you are also helping to conserve natural resources such as plastics, glass and metals as they are recovered for use again in industry.");
        Blender.setTip("It’s easy to check if an electrical item, toy or game is recyclable if it reached the end of its working life. Simply ask the following questions and if the answer is yes, to any of these, it is recyclable: Does it have a plug? Does it use batteries? Does it need charging? Does it have a picture of a crossed out wheelie bin on it?");
        mRootRef1.child("Blender").setValue(Blender);

        Material Books = new Material();
        Books.setName("Books");
        Books.setWhat("Books are made from paper and card.");
        Books.setWhere("Books should be placed in the household recycling bin.\n" +
                "Large volumes can be taken to most local civic amenity sites");
        Books.setHow("");
        Books.setTip("Books can be passed onto somebody else or if in good condition could be sold");
        mRootRef1.child("Books").setValue(Books);

        Material Cardboard = new Material();
        Cardboard.setName("Cardboard Packaging");
        Cardboard.setWhat("Cardboard packaging is generally made from heavy duty paper based material and comes in many shapes and sizes.");
        Cardboard.setWhere("This should be placed in the household recycling bin or if you have large volumes check the waste services locator");
        Cardboard.setHow("Please make sure items are clean, dry and placed loosely in the bin.");
        Cardboard.setTip("Soiled or wet cardboard cannot be recycled.");
        mRootRef1.child("Cardboard Packaging").setValue(Cardboard);

        Material Carpet = new Material();
        Carpet.setName("Carpet");
        Carpet.setWhat("Carpets are generally made from bound synthetic fibres or wool.\n" +
                "Due to the different materials used to manufacture carpets they are currently unrecyclable..");
        Carpet.setWhere("Small quantities can be placed in the household general waste bin but larger quantities should be disposed of by hiring a skip or taking the material to a waste transfer station.");
        Carpet.setHow("");
        Carpet.setTip("Why not give to a carpenter it can be reused in different ways ");

        mRootRef1.child("Carpet").setValue(Carpet);
        Material Christmastreefake = new Material();

        Christmastreefake.setName("Christmas trees(fake)");
        Christmastreefake.setWhat("Fake Christmas trees are made of many types of material which generally renders them unsuitable for recycling.\n" +
                "Many trees of this type are reused over and over.");
        Christmastreefake.setWhere("When no longer fit for purpose, fake Christmas trees should be placed in the general waste bin for disposal.");
        Christmastreefake.setHow("");
        Christmastreefake.setTip("They can be reused over and over again why not give it to fruends or family");
        mRootRef1.child("Christmas trees(fake)").setValue(Christmastreefake);

        Material Cartons = new Material();
        Cartons.setName("Cartons");
        Cartons.setWhat("Cartons are generally made of paperboard and small amounts of polyethylene with an aluminum foil lining, ");
        Cartons.setWhere("Cartons should be placed in the household recycling bin.");
        Cartons.setHow("Make sure all Cartons placed in the household recycling bin are clean and dry and placed loosely in the bin.");
        Cartons.setTip("Make sure Carton is empty when recycling ");
        mRootRef1.child("Cartons").setValue(Cartons);

        Material Christmas = new Material();
        Christmas.setName("Christmas decorations");
        Christmas.setWhat("Many Christmas decorations are made of a combination of materials which sometimes renders them unsuitable for recycling.");
        Christmas.setWhere("Any old decoration made from a single material such as paper, card or rigid plastic only can be placed in the recycling bin.\n" +
                "If you are not sure what the decoration is made of or unsure if it is made of multiple materials then place the item in the general waste bin.\n" +
                "If a decoration has any sort of plug or is powered by a battery (remove battery separately for recycling), it is considered a WEEE item and should be disposed of correctly – This should be brought to a WEEE drop-off recycling point or Civic Amenity Site");
        Christmas.setHow("");
        Christmas.setTip("Reuse your decorations each year or perhaps swap then with friends.\n" +
                "It’s easy to check if an electrical item, toy or game is recyclable if it reached the end of its working life. Simply ask the following questions and if the answer is yes, to any of these, it is recyclable: Does it have a plug? Does it use batteries? Does it need charging? Does it have a picture of a crossed out wheelie bin on it?");
        mRootRef1.child("Christmas decorations").setValue(Christmas);

        Material CDs = new Material();
        CDs.setName("CDs");
        CDs.setWhat("DVD’s & CD’s are generally made of  poly-carbonate plastic covered by an aluminium coating");
        CDs.setWhere("DVD’s & CD’s should be placed in the general waste bin.\n" +
                "The cases for both can be placed in the household recycling bin, ");
        CDs.setHow("For the cases remove paper sleeves and any soft plastic lining where possible.");
        CDs.setTip("Instead of disposing of suitable DVD’s and CD’s once you are finished with them, why not give to friends or family or donate them to a charity shop for someone else to use?\n" +
                "This is particularly relevant for music, films or box sets.");
        mRootRef1.child("CDs").setValue(CDs);

        Material freezers = new Material();
        freezers.setName("Chest freezers");
        freezers.setWhat("This is an electrical item [WEEE].");
        freezers.setWhere("This should be brought to a WEEE dropoff recycling point or Civic Amenity Site, check the waste services locator ");
        freezers.setHow("When you recycle anything with a plug or a battery you are helping to reduce the amount of waste going to landfill. Electrical items generally contain hazardous components and therefore need to be disposed of correctly. By recycling you are also helping to conserve natural resources such as plastics, glass and metals as they are recovered for use again in industry.");
        freezers.setTip("It’s easy to check if an electrical item, toy or game is recyclable if it reached the end of its working life. Simply ask the following questions and if the answer is yes, to any of these, it is recyclable: Does it have a plug? Does it use batteries? Does it need charging? Does it have a picture of a crossed out wheelie bin on it?.");

        mRootRef1.child("Chest freezers").setValue(freezers);



        /* Ash.setName("Ash");
        Ash.setWhat("Ash is produced generally from coal, wood or turf.");
        Ash.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Ash.setHow("");
        Ash.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Ash").setValue(Ash);



        Material Ash = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);

        Material Animalbedding = new Material();

        Jewellery.setName("Animal bedding & waste");
        Jewellery.setWhat("Household animal bedding generally consists of old dog and cat beds");
        Jewellery.setWhere("These items when no longer fit for purpose should be placed in the general waste bin.");
        Jewellery.setHow("");
        Jewellery.setTip("If bedding is in good condition an animal shelter may accept it as a donation.");

        mRootRef1.child("Animal bedding & waste").setValue(Animalbedding);







       */


    }


}

