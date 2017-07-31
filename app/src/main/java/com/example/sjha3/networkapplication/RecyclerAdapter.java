package com.example.sjha3.networkapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sjha3 on 7/31/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<ListObject> mArrayList;
    private Context mContext;


    public RecyclerAdapter(ArrayList<ListObject> listObjects, Context context) {
        mArrayList = listObjects;
        mContext = context;
    }

    /**
     * A function for setting data
     * @param listObjects
     */
    public void setData(ArrayList<ListObject> listObjects){
        mArrayList = listObjects;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // set the layout here
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        // bind the arraylist with the list xml layout
            holder.song_name.setText("Song Title: " + mArrayList.get(position).getSong_name());
            holder.song_year.setText("Song Year: " + mArrayList.get(position).getSong_year());
            holder.song_artist.setText("Song Author: " + mArrayList.get(position).getSong_artist());
        }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView song_name;
        private TextView song_year;
        private TextView song_artist;


        public ViewHolder(View itemView) {
            super(itemView);
            song_name = (TextView) (itemView.findViewById(R.id.song_title));
            song_year = (TextView) (itemView.findViewById(R.id.song_year));
            song_artist = (TextView) (itemView.findViewById(R.id.song_author));
        }

        @Override
        public void onClick(View view) {

        }
    }
}
