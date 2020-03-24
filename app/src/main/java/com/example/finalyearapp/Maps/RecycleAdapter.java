
package com.example.finalyearapp.Maps;

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
import com.example.finalyearapp.Maps.Location;



import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<com.example.finalyearapp.Maps.RecycleAdapter.MyViewHolder> {



    private ArrayList<Location> Locations;
    private Context mContext;
// Provide a reference to the views for each data item

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView,txtView2, txtView3, txtView4, txtView5 ;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            txtView= (TextView) itemView.findViewById(R.id.textView);
            txtView2= (TextView) itemView.findViewById(R.id.textView2);
            txtView3= (TextView) itemView.findViewById(R.id.textView3);
            txtView4= (TextView) itemView.findViewById(R.id.textView4);
            txtView5= (TextView) itemView.findViewById(R.id.textView5);



        }
    }

    public RecycleAdapter(ArrayList<Location>Locations, Context context)
    {
        this.Locations = Locations;
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
        final Location Location = Locations.get(position);

        holder.txtView.setText(Location.getName());
        holder.txtView2.setText(Location.getAddress());
        holder.txtView3.setText(Location.getCity());
        holder.txtView4.setText(Location.getState());
        holder.txtView5.setText(Location.getCoordinantes());


        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(v.getContext(), Locationview.class);
               // intent.putExtra("name" ,Location.getName());
               // v.getContext().startActivity(intent);


            }
        });

    }
    // Return the size of your dataset
    @Override
    public int getItemCount()
    {
        return Locations.size();
    }
    public void filterlist(ArrayList<Location> filteredlist){

        Locations = filteredlist;
        notifyDataSetChanged();

    }
}
