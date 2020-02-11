package com.example.finalyearapp.Leaderboard;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearapp.R;
import com.example.finalyearapp.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<User> UserList;
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

    public MyAdapter(ArrayList<User>UserList, Context context)
    {
        this.UserList = UserList;
        this.mContext = context;
    }

// Create new views (invoked by the layout manager)

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

// create a new view

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.row_layout, parent, false);
        MyViewHolder viewHolder= new MyViewHolder(itemView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //get element from your dataset at this position
        //replace the contents of the view with that element
        final User user = UserList.get(position);
        int i = 1;
        holder.txtView.setText(String.valueOf(i));
        i ++;
        holder.txtView2.setText(user.getEmail());

        holder.txtView3.setText(String.valueOf((user.getScore())));
    }
    // Return the size of your dataset
    @Override
    public int getItemCount()
    {
        return UserList.size();
    }
}
