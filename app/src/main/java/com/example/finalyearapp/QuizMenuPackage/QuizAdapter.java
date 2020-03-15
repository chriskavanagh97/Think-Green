package com.example.finalyearapp.QuizMenuPackage;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu.OceanQuizMenu;
import com.example.finalyearapp.QuizMenuPackage.PlasticQuizMenu.Quiz;
import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.pollutionQuizmenu;
import com.example.finalyearapp.R;
import com.example.finalyearapp.RecycleMaterial.Material;
import com.example.finalyearapp.RecycleMaterial.Materialview;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {



    private LayoutInflater inflater;
    private ArrayList<QuizModel> imageModelArrayList;



    public QuizAdapter(Context ctx, ArrayList<QuizModel> imageModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;


        
    }

    @Override
    public QuizAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;


    }

    @Override
    public void onBindViewHolder(QuizAdapter.MyViewHolder holder, final int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        final QuizModel model = imageModelArrayList.get(position);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Intent intent = new Intent(v.getContext(), OceanQuizMenu.class);

                intent.putExtra("name" ,model.getName());
                v.getContext().startActivity(intent);








            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView iv;


        public MyViewHolder( View itemView ) {
            super(itemView);


            iv = (ImageView) itemView.findViewById(R.id.iv);

        }

    }


}