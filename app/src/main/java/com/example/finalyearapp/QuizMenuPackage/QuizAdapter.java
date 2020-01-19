package com.example.finalyearapp.QuizMenuPackage;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearapp.R;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {

    OnClickListener monClickListener;

    private LayoutInflater inflater;
    private ArrayList<QuizModel> imageModelArrayList;



    public QuizAdapter(Context ctx, ArrayList<QuizModel> imageModelArrayList, OnClickListener onClickListener){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
        this.monClickListener = onClickListener;

        
    }

    @Override
    public QuizAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view, monClickListener);

        return holder;


    }

    @Override
    public void onBindViewHolder(QuizAdapter.MyViewHolder holder, int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView iv;
        OnClickListener onClickListener;

        public MyViewHolder(View itemView, OnClickListener onClickListener) {
            super(itemView);

            this.onClickListener = onClickListener;


            iv = (ImageView) itemView.findViewById(R.id.iv);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            onClickListener.onClickListener(getAdapterPosition());

        }
    }
    public interface OnClickListener {
        void onClickListener(int posistion);


    }

}