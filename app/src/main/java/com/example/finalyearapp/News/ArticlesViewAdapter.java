package com.example.finalyearapp.News;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.view.View.GONE;


public class ArticlesViewAdapter extends ArrayAdapter<ArticlesItem> {

    private Context mContext;
    private int layoutResourceId;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    String userid = mFirebaseAuth.getCurrentUser().getUid();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("NewsFavourites").child(userid);


    private ArrayList<ArticlesItem> mListData = new ArrayList<ArticlesItem>();


    public ArticlesViewAdapter(Context mContext, int layoutResourceId, ArrayList<ArticlesItem> mListData) {
        super(mContext, layoutResourceId, mListData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mListData = mListData;
    }


    /**
     * Updates grid data and refresh grid items.
     * @parag mListData
     */
     public void setListData(ArrayList<ArticlesItem> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        ArticlesViewAdapter.ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ArticlesViewAdapter.ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.list_item_title);
            holder.imageView = (ImageView) row.findViewById(R.id.list_item_image);
            holder.descriptionTextView = (TextView) row.findViewById(R.id.list_item_description);
            holder.companyname = row.findViewById(R.id.companyname);
            holder.date = row.findViewById(R.id.date);
            holder.favourite = row.findViewById(R.id.favourite);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        ArticlesItem item = mListData.get(position);


        holder.titleTextView.setText(Html.fromHtml(item.getTitle()));

        holder.descriptionTextView.setText(Html.fromHtml(item.getDescription()));
        holder.companyname.setText(Html.fromHtml(item.getName()));
        holder.date.setText(Html.fromHtml(item.getDate()));
        Picasso.get().load(item.getImage()).into(holder.imageView);

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Favourite clicked", Toast.LENGTH_SHORT).show();

                ArticlesItem favitem;
                favitem = new ArticlesItem();
                favitem.setTitle(item.getTitle());
                favitem.setImage(item.getImage());
                favitem.setUrl(item.getUrl());
                favitem.setDescription(item.getDescription());
                favitem.setDate(item.getDate());
                favitem.setName(item.getName());

                reference.push().setValue(favitem);

            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                view.getContext().startActivity(browserIntent);
            }
        });
        holder.companyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                view.getContext().startActivity(browserIntent);
            }
        });
        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                view.getContext().startActivity(browserIntent);
            }
        });
        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                view.getContext().startActivity(browserIntent);
            }
        });


        return row;
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        Button favourite;
        TextView descriptionTextView;
        TextView companyname, date;

    }
}
