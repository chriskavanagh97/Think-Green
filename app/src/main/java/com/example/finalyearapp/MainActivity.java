package com.example.finalyearapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearapp.Carbonfootprint.LineChartresult;
import com.example.finalyearapp.RecycleMaterial.Material;
import com.github.mikephil.charting.charts.LineChart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        ImageButton login = (ImageButton) findViewById(R.id.login);
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

        ImageButton registerButton = (ImageButton) findViewById(R.id.Register);
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

     FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
     String userid = mFirebaseAuth.getCurrentUser().getUid();


     DatabaseReference mRootRef2 = FirebaseDatabase.getInstance().getReference().child("Carbonfootprint");
    String key = mRootRef2.push().getKey();

     LineChartresult newresult = new LineChartresult();
     newresult.setxValue(new Date().getTime());
     newresult.setyValue(8);
     mRootRef2.child(userid).child("0").setValue(newresult);
          //=====================================================

     LineChartresult newresult1 = new LineChartresult();
        newresult1.setxValue(new Date().getTime());
        newresult1.setyValue(6);
     mRootRef2.child(userid).child("1").setValue(newresult1);

//===================================================

     LineChartresult newresult2 = new LineChartresult();
        newresult2.setxValue(new Date().getTime());
        newresult2.setyValue(8);
     mRootRef2.child(userid).child("2").setValue(newresult2);

     //========================================================
        LineChartresult newresult3 = new LineChartresult();
        newresult3.setxValue(new Date().getTime());
        newresult3.setyValue(11);
     mRootRef2.child(userid).child("3").setValue(newresult3);







        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("Questions");

        DatabaseReference mRootRef1 = FirebaseDatabase.getInstance().getReference().child("Materials");
        Question newQuestion = new Question();

        newQuestion.setQuestion("How many tonnes of plastic is dumped in the ocean each year");
        newQuestion.setAnswer1("8,000,000  ");
        newQuestion.setAnswer2("200,000 ");
        newQuestion.setAnswer3("10,000,000 ");
        newQuestion.setAnswer4("7,500,00");
        newQuestion.setCategory("Plastic");
        newQuestion.setDescription("We are facing a crisis, there is too much plastic in the ocean due to illegal dumping. Recycle safely or Stop using plastics");
        newQuestion.setScore(100);
        newQuestion.setId(1);

        mRootRef.child("Plastic").child("1").setValue(newQuestion);


        Question newQuestion1 = new Question();

        newQuestion1.setQuestion("What type of supermarket bag is more ecofriendly, paper or plastic?");
        newQuestion1.setAnswer1("Plastic");
        newQuestion1.setAnswer2("Paper");
        newQuestion1.setAnswer3("Neither");
        newQuestion1.setAnswer4("Either");
        newQuestion1.setDescription("Manufacturing and disposing of paper and plastic bags is bad for the environment use your own reusable bags ");
        newQuestion1.setScore(100);
        newQuestion1.setId(3);

        mRootRef.child("Plastic").child("2").setValue(newQuestion1);

        Question newQuestion2 = new Question();

        newQuestion2.setQuestion("Approximately how much global electricity output is produced from renewable sources?");
        newQuestion2.setAnswer1("25%");
        newQuestion2.setAnswer2("11%");
        newQuestion2.setAnswer3("2%");
        newQuestion2.setAnswer4("10%");
        newQuestion2.setDescription("Only 10% of energy comes from renewable sources, the other 90 comes from oil, gas and coals etc");
        newQuestion2.setScore(100);
        newQuestion2.setId(4);


        mRootRef.child("Plastic").child("3").setValue(newQuestion2);

        Question newQuestion3 = new Question();

        newQuestion3.setQuestion("Which of the following is an alternative material for making paper?");
        newQuestion3.setAnswer1("hemp");
        newQuestion3.setAnswer2("sheep skin");
        newQuestion3.setAnswer3("Panda Excrement");
        newQuestion3.setAnswer4("All of the above");
        newQuestion3.setDescription("All of these can be used although hemp is the cleanest out of them all and it easily recycled ");
        newQuestion3.setScore(100);
        newQuestion3.setId(4);


        mRootRef.child("Plastic").child("4").setValue(newQuestion3);

        Question newQuestion4 = new Question();

        newQuestion4.setQuestion("How much single use plastic bottles are thrown out each hour in the US");
        newQuestion4.setAnswer1("25,0000");
        newQuestion4.setAnswer2("12,500");
        newQuestion4.setAnswer3("20,000");
        newQuestion4.setAnswer4("1,000");
        newQuestion4.setDescription("majority of it ends up in the ocean through deliberate dumping of garbage into waterways, inefficient waste infrastructure, and simple littering.");
        newQuestion4.setScore(100);
        newQuestion4.setId(1);


        mRootRef.child("Plastic").child("5").setValue(newQuestion4);
        Question newQuestion5 = new Question();

        newQuestion5.setQuestion("What happens to plastic");
        newQuestion5.setAnswer1("It is biodegradable material os it eventually disintegrates");
        newQuestion5.setAnswer2("It never fully goes away, it just breaks into little pieces");
        newQuestion5.setAnswer3("There is no such thing as plastic waste, all plastic is recycled");
        newQuestion5.setAnswer4("It is dumped into the ocean for the fish to eat");
        newQuestion5.setDescription("Plastic will never ever disappear, it will continually break down into smaller pieces, it highlights the importance of reducing plastic usage ");
        newQuestion5.setScore(100);
        mRootRef.child("Plastic").child("6").setValue(newQuestion5);
        newQuestion5.setId(2);

        Question newQuestion6 = new Question();


        newQuestion6.setQuestion("Why are plastic bags dangerous for marine life");
        newQuestion6.setAnswer1("They mistake it for food and cannot digest it");
        newQuestion6.setAnswer2("It is not dangerous as they use it to build their habitats");
        newQuestion6.setAnswer3("They get tangled in it and can reduce their ability to swim");
        newQuestion6.setAnswer4("Both A and c");
        newQuestion6.setDescription("Plastic bags are often mistaken for  jellyfish and consumed by turtles, plastic often floats and bobbles which is why it is mistaken for marine life");
        newQuestion6.setScore(100);
        newQuestion6.setId(4);


        mRootRef.child("Plastic").child("7").setValue(newQuestion6);

        Question newQuestion7 = new Question();


        newQuestion7.setQuestion("How many marine species are harmed by plastic pollution?");
        newQuestion7.setAnswer1("52");
        newQuestion7.setAnswer2("693");
        newQuestion7.setAnswer3("1,326");
        newQuestion7.setAnswer4("693");
        newQuestion7.setDescription("A 2015 Plymouth University study compiled reports recorded from around the world and found evidence of 44,000 animals becoming entangled or swallowing plastic debris");
        newQuestion7.setScore(100);
        newQuestion7.setId(3);


        mRootRef.child("Plastic").child("8").setValue(newQuestion7);


        Question newQuestion8 = new Question();


        newQuestion8.setQuestion("What percent of its plastic does the US recycle?");
        newQuestion8.setAnswer1("70%");
        newQuestion8.setAnswer2("9%");
        newQuestion8.setAnswer3("50%");
        newQuestion8.setAnswer4("35%");
        newQuestion8.setDescription("if current production and management trends continue in the U.S., 12 billion Mt of plastic waste will end up in landfills or the ocean by 2050.");
        newQuestion8.setScore(100);
        newQuestion8.setId(2);


        mRootRef.child("Plastic").child("9").setValue(newQuestion8);

        Question newQuestion9 = new Question();

        mRootRef.child("Plastic").child("6").setValue(newQuestion5);
        newQuestion9.setQuestion("Approximately, Americans use about how many plastic drinking straws per day");
        newQuestion9.setAnswer1("500,000,000");
        newQuestion9.setAnswer2("250,000,000");
        newQuestion9.setAnswer3("100,000");
        newQuestion9.setAnswer4("200,000,000");
        newQuestion9.setDescription("On average, Americans use 1.6 plastic drinking straws every day, totaling to 500 million per day. That’s enough to fill up 125 school buses a day, or 46,400 school buses a year! ");
        newQuestion9.setScore(100);
        newQuestion9.setId(1);


        mRootRef.child("Plastic").child("10").setValue(newQuestion9);

        Question pollutionquiz = new Question();


        pollutionquiz.setQuestion("How many deaths are there annually as a result of exposure to outdoor air pollution?");
        pollutionquiz.setAnswer1("100");
        pollutionquiz.setAnswer2("200,000");
        pollutionquiz.setAnswer3("2,000,000");
        pollutionquiz.setAnswer4("3,700,000");
        pollutionquiz.setDescription("According to statistics from the UN, 3.7 million deaths are attributed to outdoor air pollution. ");
        pollutionquiz.setScore(100);
        pollutionquiz.setId(4);


        mRootRef.child("Pollution").child("1").setValue(pollutionquiz);

        Question pollutionquiz1 = new Question();


        pollutionquiz1.setQuestion("Facemasks are often used in China during air pollution episodes. What is the percentage range of ambient air particles that are filtered by standard facemasks?");
        pollutionquiz1.setAnswer1("90 - 100%");
        pollutionquiz1.setAnswer2("48 - 75%");
        pollutionquiz1.setAnswer3("26 - 40%");
        pollutionquiz1.setAnswer4("1 - 15%");
        pollutionquiz1.setDescription("A study in Particle and Fibre Toxicology tested 6 types of standard facemasks used in China and found that the most effective facemask removed only 75% of ambient air particles and the least effective mask removed 48%." +
                "It is important to realise we cause this air pollution and using face masks does not prevent our interaction with it the only prevention is recycling and a healthy green lifestyle");
        pollutionquiz1.setScore(100);
        pollutionquiz1.setId(2);


        mRootRef.child("Pollution").child("2").setValue(pollutionquiz1);

        Question pollutionquiz2 = new Question();


        pollutionquiz2.setQuestion("Does Environmental pollution exposure during pregnancy increase risk factor for pre-term (premature) birth");
        pollutionquiz2.setAnswer1("No only smoking causes this");
        pollutionquiz2.setAnswer2("no the air you breathin doesnt effect your baby");
        pollutionquiz2.setAnswer3("yes regular exposure to pollution increase chances of pre term");
        pollutionquiz2.setAnswer4("only if you are in polluted areas");
        pollutionquiz2.setDescription("Yes there is umerous studies that increased access to pollution can affect pregnancy. We need to remove this pollution");
        pollutionquiz2.setScore(100);
        pollutionquiz2.setId(3);


        mRootRef.child("Pollution").child("3").setValue(pollutionquiz2);

        Question pollutionquiz3 = new Question();
        pollutionquiz3.setQuestion("Exposure to indoor second hand smoke, dirty cooking fuel, and dampness increases the risk of which infectious disease in children?");
        pollutionquiz3.setAnswer1("Malaria");
        pollutionquiz3.setAnswer2("Mumps");
        pollutionquiz3.setAnswer3("Cholera");
        pollutionquiz3.setAnswer4("Tuberculosis");
        pollutionquiz3.setDescription("A study based in Durban, South Africa published in BMC Public Health suggests an increased risk of childhood tuberculosis when exposed to these types of indoor air pollutants.");
        pollutionquiz3.setScore(100);
        pollutionquiz3.setId(4);

        mRootRef.child("Pollution").child("4").setValue(pollutionquiz3);
        Question pollutionquiz4 = new Question();
        pollutionquiz4.setQuestion("How much CO2 was removed from the atmosphere by Brazilian forest plantations between 1990 and 2016?");
        pollutionquiz4.setAnswer1("1669 Gigatonnes");
        pollutionquiz4.setAnswer2("10 gigatonnes");

        pollutionquiz4.setAnswer3("100 gigatones");
        pollutionquiz4.setAnswer4("700,000 tonnes");
        pollutionquiz4.setDescription("1669 Gigatonnes of CO2 over the 26 year period. 1 Gigatonne is equal to 1 billion tonnes. This study emphasizes the important role forests play in mitigating greenhouse gas emissions");
        pollutionquiz4.setScore(100);
        pollutionquiz4.setId(1);

        mRootRef.child("Pollution").child("5").setValue(pollutionquiz4);



        Question pollutionquiz5 = new Question();
        pollutionquiz5.setQuestion("Which of the following is the most effective strategy to reduce the impact of toxic waste");
        pollutionquiz5.setAnswer1("stopping the production of polluting materials");
        pollutionquiz5.setAnswer2("raising taxes on companies that pollute");
        pollutionquiz5.setAnswer3("recycling polluting materials");
        pollutionquiz5.setAnswer4("reusing raw materials");
        pollutionquiz5.setDescription("Toxins can be reduced through the substitution of nonpolluting alternatives, such as oxygen for chlorine in the bleaching of wood, or through green chemistry");
        pollutionquiz5.setScore(100);
        pollutionquiz5.setId(1);

        mRootRef.child("Pollution").child("6").setValue(pollutionquiz5);

        Question ocean = new Question();
        ocean.setQuestion("Other than melting land-based ice sheets, which of these factors has made the largest contribution to the rise in sea level over the past 100 years?");
        ocean.setAnswer1("Climate change including Rain");
        ocean.setAnswer2("melting sea ice");
        ocean.setAnswer3("warming of ocean surface waters");
        ocean.setAnswer4("increased river run offs");
        ocean.setDescription("As the ocean warms, it expands and sea level rises, accounting for about a third of the approximately 20-centimeter sea level rise seen in the past century. Water released by melting land-based ice sheets contributes the other two-thirds of sea level rise");
        ocean.setScore(100);
        ocean.setId(3);

        mRootRef.child("Ocean").child("1").setValue(ocean);

        Question ocean2 = new Question();
        ocean2.setQuestion("What percentage of heat from global warming has the ocean absorbed in the past 40 years?");
        ocean2.setAnswer1("20%");
        ocean2.setAnswer2("93%");
        ocean2.setAnswer3("5%");
        ocean2.setAnswer4("66%");
        ocean2.setDescription("Water resists changes in temperature; it is slow to heat up and slow to cool down. In scientific terms, water has high heat capacity. This means that, so far, Earth's ocean has been able to absorb and hold a majority of the heat from Earth's atmosphere" +
                "Although as we continue to cause a rise in global warming the ocean will absorb less and we will begin to feel the full effects ");
        ocean2.setScore(100);
        ocean2.setId(2);

        mRootRef.child("Ocean").child("2").setValue(ocean2);


        Question ocean3 = new Question();
        ocean3.setQuestion("Melting sea ice has the potential to raise sea level by how many metres");
        ocean3.setAnswer1("15");
        ocean3.setAnswer2("30");
        ocean3.setAnswer3("0");
        ocean3.setAnswer4("35");
        ocean3.setDescription("Melting sea ice cannot raise global sea level since the ice is already floating. (Think of an ice cube melting in a glass full of water.) However, Arctic sea ice is thinning and the long-term summer average has decreased by 34 percent since 1979. Ice from glaciers and ice sheets, which form on land, does add water to Earth's ocean when it melts and does contribute to sea level rise.");
        ocean3.setScore(100);
        ocean3.setId(3);

        mRootRef.child("Ocean").child("3").setValue(ocean3);


        Question ocean4 = new Question();
        ocean4.setQuestion("What percentage of the world's population lives within 100 kilometers of the shoreline?");
        ocean4.setAnswer1("10");
        ocean4.setAnswer2("80%");
        ocean4.setAnswer3("25%");
        ocean4.setAnswer4("39%");
        ocean4.setDescription("According to the World Resources Institute, in 1995 2.2 billion people, or 39 percent of the world's population, lived on or within 100 kilometers of a seashore. Recent studies reveal that up to 600 million people live in Low Elevation Coastal Zones and 200 million people live within coastal flood plains. ");
        ocean4.setScore(100);
        ocean4.setId(4);

        mRootRef.child("Ocean").child("4").setValue(ocean4);



        Question ocean6 = new Question();
        ocean6.setQuestion("What area of the ocean is suffering the most from habitat destruction?");
        ocean6.setAnswer1("Ocean Floor");
        ocean6.setAnswer2("coastal");
        ocean6.setAnswer3("Coral reefs");
        ocean6.setAnswer4("Deep Sea");
        ocean6.setDescription("Most areas of the world’s ocean are experiencing habitat loss, but coastal areas with their closeness to human population centres, have suffered disproportionately. Habitat los here has far reaching impacts on the entire ocean’s biodiversity. These critical areas which include estuaries, swamps, marshes and wetlands, serve as breeding grounds or nurseries for nearly all marine species");
        ocean6.setScore(100);
        ocean6.setId(2);

        mRootRef.child("Ocean").child("6").setValue(ocean6);



        Question ocean7 = new Question();
        ocean7.setQuestion("Industrial fishing is estimated to have wiped out what percent of large predatory fish?");
        ocean7.setAnswer1("10 percent");
        ocean7.setAnswer2("20 percent");
        ocean7.setAnswer3("60 percent");
        ocean7.setAnswer4("90 percent");
        ocean7.setDescription("Industrial finishing accounts for wiping out 90% of large predatory fish, such as swordfish, marlin, and the biggest types of tuna. Since 1950, more than 50 million tons of tuna and other top predators have been taken from the pacific.");
        ocean7.setScore(100);
        ocean7.setId(3);

        mRootRef.child("Ocean").child("7").setValue(ocean7);


        Question ocean8 = new Question();
        ocean8.setQuestion(" Ocean acidification is putting which animals most directly at risk?");
        ocean8.setAnswer1("Marine animals");
        ocean8.setAnswer2("Bottom feeding fish");
        ocean8.setAnswer3("Sea birds");
        ocean8.setAnswer4("Shelled Animals");
        ocean8.setDescription("When Carbon dioxide dissolves in the ocean, carbonic acid is formed. This leads to higher acidity, mainly near the surface, which has ben proven to inhibit shell growth in marine animals. Its also suspected as a cause of reproductive disorders in some fish");
        ocean8.setScore(100);
        ocean8.setId(4);

        mRootRef.child("Ocean").child("8").setValue(ocean8);

        Question ocean9 = new Question();
        ocean9.setQuestion("Industrial fishing is estimated to have wiped out what percent of large predatory fish?");
        ocean9.setAnswer1("Bottom feeding fish");
        ocean9.setAnswer2("Shelled Animals");
        ocean9.setAnswer3("Sea birds");
        ocean9.setAnswer4("Marine Animals");
        ocean9.setDescription("Thi sis the cause of over fishing, this results in a complete destruction of the habitat of fishes which further affects the environment ");
        ocean9.setScore(100);
        ocean9.setId(1);

        mRootRef.child("Ocean").child("9").setValue(ocean9);



        Question ocean10 = new Question();
        ocean10.setQuestion("Where does most ocean pollution come from?");
        ocean10.setAnswer1("Land activities");
        ocean10.setAnswer2("Marine Activities");
        ocean10.setAnswer3("All three are divided equally");
        ocean10.setAnswer4("Natural & unpreventable causes");
        ocean10.setDescription("More than 80 percent of all ocean pollution comes from land. Oil from cities and factories washes down drains into the ocean, runoff from files and lawns carries fertilizer to the sea, and tons of solid garbage – most of it plastic – winds up bobbing in the ocean. ");
        ocean10.setScore(100);
        ocean10.setId(1);

        mRootRef.child("Ocean").child("10").setValue(ocean8);


        Question ocean11 = new Question();
        ocean11.setQuestion("Elevated water temperatures can cause coral to do what?");
        ocean11.setAnswer1("All of the above");
        ocean11.setAnswer2("Eat itself");
        ocean11.setAnswer3("Grow too quickly");
        ocean11.setAnswer4("Bleach");
        ocean11.setDescription("Coral reefs are in trouble across the globe due to the rising greenhouse gas levels heat the ocean. Elevated temperatures trigger mass episodes of bleaching, in which corals eject their symbiotic algae. Bleaching slows coral growth, makes them susceptible to disease, and can lead to large – scale reef die offs");
        ocean11.setScore(100);
        ocean11.setId(4);

        mRootRef.child("Ocean").child("11").setValue(ocean11);

        Question ocean12 = new Question();
        ocean12.setQuestion("Which ocean's fish stocks have taken the hardest hit from overfishing?");
        ocean12.setAnswer1("West Indies, Atlantic Ocean");
        ocean12.setAnswer2("Phoenix Islands, Pacific Ocean");
        ocean12.setAnswer3("Andaman Islands, Indian Ocean");
        ocean12.setAnswer4("Hawaiian Islands");
        ocean12.setDescription("The Phoenix Islands Protected Area, established by the tiny Pacific nation of Kiribati in 2006, is the worlds largest marine protected area. A swath of ocean the size of California protects pristine reefs and eight atolls teeming with fish and birds");
        ocean12.setScore(100);
        ocean12.setId(2);

        mRootRef.child("Ocean").child("12").setValue(ocean12);



        Question ocean13 = new Question();
        ocean13.setQuestion("Which ocean's fish stocks have taken the hardest hit from overfishing?");
        ocean13.setAnswer1("Pacific");
        ocean13.setAnswer2("Artic");
        ocean13.setAnswer3("Atlantic");
        ocean13.setAnswer4("Indian");
        ocean13.setDescription("Nearly a third of the worlds fish stocks are over fished and the Atlantic has take then hardest hit. In many regions of the Atlantic, more than 45 Percent of stocks are overfished.");
        ocean13.setScore(100);
        ocean13.setId(3);

        mRootRef.child("Ocean").child("13").setValue(ocean13);

        Question ocean15 = new Question();
        ocean15.setQuestion("World ocean temperatures set a record in 2009. What was the average temperature worldwide in July?");
        ocean15.setAnswer1("14C");
        ocean15.setAnswer2("24.6C");
        ocean15.setAnswer3("29.3C");
        ocean15.setAnswer4("17C");
        ocean15.setDescription("In July 2009, the worlds oceans set a record for high temperatures. The average water temperature worldwide was 17 degrees Celsius, according to the national Climatic Data Centre. Global warming is a direct factor is sending ocean temperatures this high");
        ocean15.setScore(100);
        ocean15.setId(4);

        mRootRef.child("Ocean").child("15").setValue(ocean15);

        Question ocean14 = new Question();
        ocean14.setQuestion("How much has the global mean sea level risen over the last century?");
        ocean14.setAnswer1("4 - 8 inches");
        ocean14.setAnswer2("1 to 3 inches");
        ocean14.setAnswer3("15 to 20 inches");
        ocean14.setAnswer4("Not at all");
        ocean14.setDescription("Core samples, tide gauge readings and, most recently the satellite measurements tell us that over the last century, the global mean sea level has risen by 4 to 8 inches. When sea levels rise rapidly, even a small increase can have the devastating effects on coastal habitats.");
        ocean14.setScore(100);
        ocean14.setId(1);

        mRootRef.child("Ocean").child("14").setValue(ocean14);


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

