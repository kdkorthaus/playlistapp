package com.example.songlist;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Playlist extends AppCompatActivity {

    private Song currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        currentSong = new Song();
        initAddButton();
        initPlaylistButton();
        initTextChangeEvents();
        initSaveButton();
    }


    private void initAddButton() {
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Playlist.this, MainActivity.class);
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
                Intent intent = new Intent(Playlist.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initTextChangeEvents() {
        EditText titleEdit = findViewById(R.id.editTitle);
        titleEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentSong.setTitle(titleEdit.getText().toString());
                currentSong.setSongID(-1);
            }
        });

        EditText artistEdit = findViewById(R.id.editArtist);
        artistEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentSong.setArtist(artistEdit.getText().toString());
            }
        });

        EditText albumEdit = findViewById(R.id.editAlbum);
        albumEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentSong.setAlbum(albumEdit.getText().toString());
            }
        });
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                SongDataSource ds = new SongDataSource(Playlist.this);
                try {
                    ds.open();
                    if (currentSong.getSongID() == -1) {
                        Toast.makeText(getBaseContext(), currentSong.toString(), Toast.LENGTH_LONG).show();
                        wasSuccessful = ds.insertSong(currentSong);
                        if (wasSuccessful) {
                            int newId = ds.getLastSongID();
                            currentSong.setSongID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateSong(currentSong);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                saveButton.setText(""+currentSong.getSongID());
            }
        });
    }
}
