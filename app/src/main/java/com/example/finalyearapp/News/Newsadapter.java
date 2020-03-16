package com.example.finalyearapp.News;


import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearapp.R;

import java.util.ArrayList;

public class Newsadapter extends RecyclerView.Adapter<Newsadapter.MyViewHolder> {



    private LayoutInflater inflater;
    private ArrayList<Newsmenumodel> imageModelArrayList;



    public Newsadapter(Context ctx, ArrayList<Newsmenumodel> imageModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;



    }

    @Override
    public Newsadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        Newsadapter.MyViewHolder holder = new MyViewHolder(view);

        return holder;


    }

    @Override
    public void onBindViewHolder(Newsadapter.MyViewHolder holder, final int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        final Newsmenumodel model = imageModelArrayList.get(position);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(v.getContext(), ArticlesActivity.class);

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