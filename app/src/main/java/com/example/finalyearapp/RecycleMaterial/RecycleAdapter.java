package com.example.finalyearapp.RecycleMaterial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.example.finalyearapp.R;


import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<com.example.finalyearapp.RecycleMaterial.RecycleAdapter.MyViewHolder> {



    private ArrayList<Material> materials;
    private Context mContext;
// Provide a reference to the views for each data item

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView,txtView2, txtView3 ;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            txtView= (TextView) itemView.findViewById(R.id.textView);
            txtView2= (TextView) itemView.findViewById(R.id.textView2);
            txtView3= (TextView) itemView.findViewById(R.id.textView3);



        }
    }

    public RecycleAdapter(ArrayList<Material>materials, Context context)
    {
        this.materials = materials;
        this.mContext = context;
    }

// Create new views (invoked by the layout manager)

    @Override
    public com.example.finalyearapp.RecycleMaterial.RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

// create a new view

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.recycler_layout, parent, false);
        com.example.finalyearapp.RecycleMaterial.RecycleAdapter.MyViewHolder viewHolder= new com.example.finalyearapp.RecycleMaterial.RecycleAdapter.MyViewHolder(itemView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(com.example.finalyearapp.RecycleMaterial.RecycleAdapter.MyViewHolder holder, int position) {

        //get element from your dataset at this position
        //replace the contents of the view with that element
        final Material material = materials.get(position);

        holder.txtView.setText(material.getName());
        holder.txtView2.setText(material.getWhere());
        holder.txtView3.setText(material.getWhat());


        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Materialview.class);
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
