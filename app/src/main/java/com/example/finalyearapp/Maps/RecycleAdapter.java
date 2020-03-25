
package com.example.finalyearapp.Maps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearapp.R;


import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<com.example.finalyearapp.Maps.RecycleAdapter.MyViewHolder> {



    private ArrayList<place> places;
    private Context mContext;
// Provide a reference to the views for each data item

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView,txtView2, txtView3, txtView4 ;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            txtView= (TextView) itemView.findViewById(R.id.textView);
            txtView2= (TextView) itemView.findViewById(R.id.textView2);
            txtView3= (TextView) itemView.findViewById(R.id.textView3);
            txtView4= (TextView) itemView.findViewById(R.id.textView4);



        }
    }

    public RecycleAdapter(ArrayList<place> places, Context context)
    {
        this.places = places;
        this.mContext = context;
    }

// Create new views (invoked by the layout manager)

    @Override
    public com.example.finalyearapp.Maps.RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

// create a new view

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.locationslayout, parent, false);
        com.example.finalyearapp.Maps.RecycleAdapter.MyViewHolder viewHolder= new com.example.finalyearapp.Maps.RecycleAdapter.MyViewHolder(itemView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(com.example.finalyearapp.Maps.RecycleAdapter.MyViewHolder holder, int position) {

        //get element from your dataset at this position
        //replace the contents of the view with that element
        final place place = places.get(position);

        holder.txtView.setText(place.getName());
        holder.txtView2.setText(place.getAddress());
        holder.txtView3.setText(place.getCity());
        holder.txtView4.setText(place.getState());


        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(v.getContext(), MapsActivity.class);
               intent.putExtra("lat" , place.getLat());
               intent.putExtra("lng" , place.getLng());
               intent.putExtra("address" , place.getAddress());
               intent.putExtra("city" , place.getCity());
               intent.putExtra("name" , place.getName());

               intent.putExtra("value", "true");
                v.getContext().startActivity(intent);

            }
        });

    }
    // Return the size of your dataset
    @Override
    public int getItemCount()
    {
        return places.size();
    }
    public void filterlist(ArrayList<place> filteredlist){

        places = filteredlist;
        notifyDataSetChanged();

    }
}
