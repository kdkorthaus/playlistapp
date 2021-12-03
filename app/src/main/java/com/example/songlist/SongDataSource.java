package com.example.songlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SongDataSource {

    private SQLiteDatabase database;
    private SongDBHelper dbhelper;

    public SongDataSource(Context context) {dbhelper = new SongDBHelper(context);}

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    public void close() {dbhelper.close();}

    public boolean insertSong(Song s) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            Log.d("*******INSERT********", s.getTitle());
            values.put("title", s.getTitle());
            values.put("artist", s.getArtist());
            values.put("album", s.getAlbum());
            didSucceed = database.insert("song", null, values) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public boolean updateSong(Song s) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("title", s.getTitle());
            values.put("artist", s.getArtist());
            values.put("album", s.getAlbum());
            Long id = (long) s.getSongID();
            didSucceed = database.update("song", values, "_id="+id, null) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public int getLastSongID() {
        int lastID = -1;
        try {
            String query = "Select MAX(_id) from song";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
        } catch (Exception e) {

        }
        return lastID;
    }

    public ArrayList<Song> getSongs() {
        ArrayList<Song> song = new ArrayList<Song>();
        try {
            String query = "Select * from song";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Song s = new Song();
                s.setTitle(cursor.getString(1));
                s.setArtist(cursor.getString(2));
                s.setAlbum(cursor.getString(3));
                song.add(s);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {

        }
        return song;
    }

    public Song getSong(int id) {
        Song s = new Song();
        try {
            String query = "Select * from song where _id="+id;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            s.setSongID(id);
            s.setTitle(cursor.getString(1));
            s.setArtist(cursor.getString(2));
            s.setAlbum(cursor.getString(3));
            cursor.close();
        } catch (Exception e) {

        }
        return s;
    }
}
