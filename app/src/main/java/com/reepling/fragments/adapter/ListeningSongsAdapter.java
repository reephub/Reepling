package com.reepling.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.reepling.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Michaël on 26/02/2018.
 */

public class ListeningSongsAdapter extends  RecyclerView.Adapter<ListeningSongsAdapter.MyViewHolder> {

    private static final String TAG = ListeningSongsAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<HashMap<String, String>> songsList;
    private ListeningSongsAdapterListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.item_listening_title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onSongSelected( songsList.get(getAdapterPosition() ).toString() );
                    listener.onSongSelected( getAdapterPosition() );
                }
            });
        }
    }

    public ListeningSongsAdapter (Context context, ArrayList<HashMap<String, String>> songsList, ListeningSongsAdapterListener listener) {
        this.context = context;
        this.songsList = songsList;
        this.listener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_listening_songs, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String song = songsList.get(position).get("songTitle");
        holder.title.setText( song );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public interface ListeningSongsAdapterListener {
        void onSongSelected(String song);
        void onSongSelected(int position);
    }

}
