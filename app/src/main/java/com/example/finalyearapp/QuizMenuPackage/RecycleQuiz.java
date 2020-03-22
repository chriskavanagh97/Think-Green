package com.example.finalyearapp.QuizMenuPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalyearapp.QuizMenuPackage.PlasticQuizMenu.Quiz;
import com.example.finalyearapp.R;

public class RecycleQuiz extends AppCompatActivity {

    TextView question, solution, next;
    ImageView picture;
    Button yes, no;
    int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_quiz);

        Quiz();
        picture = findViewById(R.id.imageView6);
        question = findViewById(R.id.question);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        next = findViewById(R.id.next);
    }

    public void Quiz(){

        if(total >11){

        }
        else

        {
            if(total == 0)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText("Correct! \n" +
                                "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }
            else if(total == 1)
            {
                picture.setImageResource(R.drawable.pizzabox);
                question.setText("Can you recycle pizza boxes");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "It depends on the municipality, but most don't accept pizza boxes, because the grease contaminates them, making them unrecyclable. Why? Well, when paper is recycled, it's mixed with water to form a slurry, and because oil and water don't mix, food grease and oil float to the top of the slurry, mingle with the paper pulp, and render the whole batch unusable.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText("Correct! \n" +
                                "It depends on the municipality, but most don't accept pizza boxes, because the grease contaminates them, making them unrecyclable. Why? Well, when paper is recycled, it's mixed with water to form a slurry, and because oil and water don't mix, food grease and oil float to the top of the slurry, mingle with the paper pulp, and render the whole batch unusable.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }
            else if(total == 2)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText("Wrong! \n" +
                                "Most receipts are made from thermal paper and contain Bisphenol A, which shouldn't enter the recycling stream (or else your receipt would become toilet paper and give your backside some unwanted quality time with that chemical). The move here is to politely decline a receipt or opt for an electronic version whenever possible.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }
            else if(total == 3)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText("Correct! \n" +
                                "Whether it's glossy, non-glossy, construction, or a plain envelope, that mail can be recycled. It's doesn't hurt to remove the clear window panes from envelopes, but most are actually made from cellulose, which can be recycled with paper. Want to stop that junk mail altogether? ");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }
            else if(total == 4)
        {
            picture.setImageResource(R.drawable.aluminium);
            question.setText("Can you recycle aluminium cans");

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    solution.setText("Wrong! \n" +
                            "+When it comes to items like bagged salad, chips, cookies, and candy, if the bag is soft enough to crush in your hand, it’s usually not recyclable. Some special receptacles do exist for these, but your best bet is to avoid this type of packaging as much as you can..");
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    solution.setText(" Wrong! \n" +
                            "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    total++;
                    Quiz();
                }
            });

        }
            else if(total == 5)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "Amber, emerald, and clear glass can all come back as other glass products for basically all of eternity. But you should still try to repurpose 'em as much as possible before you toss 'em in your recycling");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }
            else if(total == 6)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText("Correct! \n" +
                                "Pretty much all plastic bottles, jugs, and jars (save for yogurt or pudding containers, which may need to be recycled elsewhere) are recyclable. Just be sure to rinse them out beforehand!");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }
            else if(total == 7)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText("Correct! \n" +
                                "Aluminum cans are able to become other aluminum products over and over and over again. Remember to give them a quick rinse, but please don't crush them before putting them in your bin — crushed cans can miss the sorter and end up in the landfill.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "Sadly, even though mirrors might seem like glass, lots of chemicals go into making them reflective, and those chemicals can't safely enter recycling streams. Womp.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }else if(total == 8)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Some special systems can handle styrofoam, but they’re not very common, so when you can, refuse things like packing peanuts and coated cups and containers...since styrofoam can sit in landfills for centuries.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "Sadly, even though mirrors might seem like glass, lots of chemicals go into making them reflective, and those chemicals can't safely enter recycling streams. Womp.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }else if(total == 9)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "There's a misconception that glossy paper can't be recycled, but that shiny coating doesn't actually contaminate the paper, so these days, nearly all community recycling programs will accept glossy magazines and catalogs.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "Sadly, even though mirrors might seem like glass, lots of chemicals go into making them reflective, and those chemicals can't safely enter recycling streams. Womp.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }else if(total == 10)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Ceramics contain a bunch of chemicals that make them ultra-heat tolerant and, like mirrors, those chemicals don't belong in recycling streams.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "Sadly, even though mirrors might seem like glass, lots of chemicals go into making them reflective, and those chemicals can't safely enter recycling streams. Womp.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }else if(total == 8)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Some special systems can handle styrofoam, but they’re not very common, so when you can, refuse things like packing peanuts and coated cups and containers...since styrofoam can sit in landfills for centuries.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "Sadly, even though mirrors might seem like glass, lots of chemicals go into making them reflective, and those chemicals can't safely enter recycling streams. Womp.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }else if(total == 8)
            {
                picture.setImageResource(R.drawable.aluminium);
                question.setText("Can you recycle aluminium cans");

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Wrong! \n" +
                                "Some special systems can handle styrofoam, but they’re not very common, so when you can, refuse things like packing peanuts and coated cups and containers...since styrofoam can sit in landfills for centuries.");
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        solution.setText(" Correct! \n" +
                                "Sadly, even though mirrors might seem like glass, lots of chemicals go into making them reflective, and those chemicals can't safely enter recycling streams. Womp.");
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        total++;
                        Quiz();
                    }
                });

            }



        }

    }
}
