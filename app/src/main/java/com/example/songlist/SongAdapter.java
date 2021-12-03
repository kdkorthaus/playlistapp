package com.example.songlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter {

    private ArrayList<Song> songData;
    private View.OnClickListener onClickListener;

    public class SongViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewSong;
        public TextView textViewArtist;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSong = itemView.findViewById(R.id.textViewTitle);
            textViewArtist = itemView.findViewById(R.id.textViewArtist);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getSongTextView(){return textViewSong;}

        public TextView getArtistTextView(){return textViewArtist;}

    }

    public void setOnClickListener(View.OnClickListener listener) {
        onClickListener = listener;
    }

    public SongAdapter(ArrayList<Song> arrayList) {
        songData = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new SongViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SongViewHolder cvh = (SongViewHolder) holder;
        cvh.getSongTextView().setText("title"+songData.get(position).getTitle());
        cvh.getArtistTextView().setText("artist"+songData.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return songData.size();
    }
}
