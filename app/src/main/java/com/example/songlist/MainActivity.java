package com.example.songlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Song currentSong;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAddButton();
        initPlaylistButton();
    }

    public void onResume() {
        super.onResume();
        //Intent intent = getIntent();
        //int position = intent.getIntExtra("position", -1);
        //Toast.makeText(this, "Position "+position, Toast.LENGTH_LONG).show();
            SongDataSource ds = new SongDataSource(this);
            ArrayList<Song> songs;
            try {
                ds.open();
                songs = ds.getSongs();
                ds.close();
                Toast.makeText(this, "Song "+songs.size(), Toast.LENGTH_LONG).show();
                RecyclerView songList = findViewById(R.id.rvSongs);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                songList.setLayoutManager(layoutManager);
                SongAdapter songAdapter = new SongAdapter(songs);
                songAdapter.setOnClickListener(onClickListener);
                songList.setAdapter(songAdapter);
            } catch (Exception e) {
                Toast.makeText(this, "Error accessing contact", Toast.LENGTH_LONG).show();
            }
    }

    private void initAddButton() {
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Playlist.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initPlaylistButton() {
        Button playlistButton = findViewById(R.id.playlistButton);
        playlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}