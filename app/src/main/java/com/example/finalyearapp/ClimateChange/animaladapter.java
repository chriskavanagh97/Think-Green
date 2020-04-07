package com.example.finalyearapp.ClimateChange;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearapp.MainActivity;
import com.example.finalyearapp.R;
import com.example.finalyearapp.RecycleMaterial.Material;


import java.util.ArrayList;

public class animaladapter extends RecyclerView.Adapter<com.example.finalyearapp.ClimateChange.animaladapter.MyViewHolder> {



    private ArrayList<Material> materials;
    private Context mContext;
// Provide a reference to the views for each data item

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView,txtView2 ;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            txtView= (TextView) itemView.findViewById(R.id.textView);
            txtView2 = itemView.findViewById(R.id.textView2);

        }
    }

    public animaladapter(ArrayList<Animal>animals, Context context)
    {
        this.materials = materials;
        this.mContext = context;
    }

// Create new views (invoked by the layout manager)

    @Override
    public com.example.finalyearapp.ClimateChange.animaladapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

// create a new view

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.recycler_layout, parent, false);
        com.example.finalyearapp.ClimateChange.animaladapter.MyViewHolder viewHolder= new com.example.finalyearapp.ClimateChange.animaladapter.MyViewHolder(itemView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(com.example.finalyearapp.ClimateChange.animaladapter.MyViewHolder holder, int position) {

        //get element from your dataset at this position
        //replace the contents of the view with that element
        final Material material = materials.get(position);

        holder.txtView.setText(material.getName());

        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AnimalView.class);
                intent.putExtra("name" ,material.getName());
                v.getContext().startActivity(intent);


            }
        });

    }
    // Return the size of your dataset
    @Override
    public int getItemCount()
    {
        return materials.size();
    }

    public void filterlist(ArrayList<Material> filteredlist){

        materials = filteredlist;
        notifyDataSetChanged();

    }
}