package com.example.finalyearapp.ClimateChange;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;





import com.example.finalyearapp.R;



import java.util.ArrayList;

public class animaladapter extends RecyclerView.Adapter<com.example.finalyearapp.ClimateChange.animaladapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Animal> imageModelArrayList;


    private Context mContext;
// Provide a reference to the views for each data item


    public animaladapter(Context ctx, ArrayList<Animal> imageModelArrayList) {

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;


    }


    // Create new views (invoked by the layout manager)
    @Override
    public animaladapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.animalrecyler, parent, false);
        animaladapter.MyViewHolder holder = new animaladapter.MyViewHolder(view);

        return holder;


    }


    @Override
    public void onBindViewHolder(com.example.finalyearapp.ClimateChange.animaladapter.MyViewHolder holder, int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        final Animal model = imageModelArrayList.get(position);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), AnimalMenu.class);

                intent.putExtra("name", model.getName());
                v.getContext().startActivity(intent);


            }
        });
    }

    // Return the size of your dataset
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView iv;


        public MyViewHolder(View itemView) {
            super(itemView);


            iv = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}