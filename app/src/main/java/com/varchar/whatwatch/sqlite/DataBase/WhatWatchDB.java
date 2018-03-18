package com.varchar.whatwatch.sqlite.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.varchar.whatwatch.model.Genre;
import com.varchar.whatwatch.model.VideoMedia;
import com.varchar.whatwatch.sqlite.contrat.VideoMediaEntry;

import java.util.ArrayList;
import java.util.List;

public class WhatWatchDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "WhatWatch.db";

    private  static  final String sqlCreate = "CREATE TABLE " + VideoMediaEntry.TABLE_NAME + " ("
            + VideoMediaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VideoMediaEntry.ID + " INTEGER,"
            + VideoMediaEntry.NAME + " TEXT NOT NULL,"
            + VideoMediaEntry.LOCAL_IMAGE_ID + " INTEGER NOT NULL,"
            + VideoMediaEntry.IMAGE_URL + " TEXT,"
            + VideoMediaEntry.DETAIL_IMAGE_URL + " TEXT,"
            + VideoMediaEntry.GENRE + " TEXT,"
            + VideoMediaEntry.DESCRIPTION + " TEXT,"
            + VideoMediaEntry.RELEASE_DATE + " TEXT,"
            + VideoMediaEntry.TYPE + " TEXT,"
            + "UNIQUE (" + VideoMediaEntry.NAME + "))";

    private static WhatWatchDB dbInstance;

    private WhatWatchDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static WhatWatchDB getInstance(Context context){
        if(dbInstance == null){
            dbInstance = new WhatWatchDB(context);
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VideoMediaEntry.TABLE_NAME);
        db.execSQL(sqlCreate);
    }


    public static long saveFavourite(VideoMedia videoMedia) {
        SQLiteDatabase sqLiteDatabase = dbInstance.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VideoMediaEntry.ID, videoMedia.getId());
        values.put(VideoMediaEntry.NAME, videoMedia.getName());
        values.put(VideoMediaEntry.LOCAL_IMAGE_ID, videoMedia.getImageId());
        values.put(VideoMediaEntry.IMAGE_URL, videoMedia.getImageUrl());
        values.put(VideoMediaEntry.DETAIL_IMAGE_URL, videoMedia.getDetailImageUrl());
//        values.put(VideoMediaEntry.GENRE, videoMedia.getGender().getName());
        values.put(VideoMediaEntry.DESCRIPTION, videoMedia.getDescription());
        values.put(VideoMediaEntry.RELEASE_DATE, videoMedia.getReleaseDate());
        values.put(VideoMediaEntry.TYPE, videoMedia.getType().toString());


        return sqLiteDatabase.insert(
                VideoMediaEntry.TABLE_NAME,
                null,
                values);
    }

    public static boolean isFavoutie(VideoMedia videoMedia){
        String query = "SELECT * FROM " + VideoMediaEntry.TABLE_NAME + " WHERE " + VideoMediaEntry.NAME + "=?";
        Cursor cursor = dbInstance.getReadableDatabase().rawQuery(query, new String[]{videoMedia.getName()});
        return cursor.moveToNext();
    }

    public static void deleteFavourite(VideoMedia videoMedia){
        dbInstance.getWritableDatabase().delete(VideoMediaEntry.TABLE_NAME,
                VideoMediaEntry.NAME + "=?" , new String[]{videoMedia.getName()});
    }


    public static List<VideoMedia> getAllFavourites(){
        SQLiteDatabase sqLiteDatabase = dbInstance.getReadableDatabase();

        ArrayList<VideoMedia> favourites= new ArrayList<>();
        Cursor c = sqLiteDatabase.query(
                VideoMediaEntry.TABLE_NAME,
                new String[]{
                        VideoMediaEntry.ID,
                        VideoMediaEntry.NAME,
                        VideoMediaEntry.LOCAL_IMAGE_ID,
                        VideoMediaEntry.IMAGE_URL,
                        VideoMediaEntry.DETAIL_IMAGE_URL,
                        VideoMediaEntry.GENRE,
                        VideoMediaEntry.DESCRIPTION,
                        VideoMediaEntry.RELEASE_DATE,
                        VideoMediaEntry.TYPE
                },
                null,
                null,
                null,
                null,
                null
        );

        while(c.moveToNext()){
            VideoMedia videoMedia = new VideoMedia();
            videoMedia.setId( c.getInt(c.getColumnIndex(VideoMediaEntry.ID)));
            videoMedia.setName( c.getString(c.getColumnIndex(VideoMediaEntry.NAME)));
            videoMedia.setImageId( c.getInt(c.getColumnIndex(VideoMediaEntry.LOCAL_IMAGE_ID)));
            videoMedia.setImageUrl( c.getString(c.getColumnIndex(VideoMediaEntry.IMAGE_URL)));
            videoMedia.setDetailImageUrl( c.getString(c.getColumnIndex(VideoMediaEntry.DETAIL_IMAGE_URL)));
            videoMedia.setGender( new Genre(c.getString(c.getColumnIndex(VideoMediaEntry.GENRE))));
            videoMedia.setDescription( c.getString(c.getColumnIndex(VideoMediaEntry.DESCRIPTION)));
            videoMedia.setReleaseDate( c.getString(c.getColumnIndex(VideoMediaEntry.RELEASE_DATE)));
            videoMedia.setType(c.getString(c.getColumnIndex(VideoMediaEntry.TYPE)));
            favourites.add(videoMedia);
        }
        return favourites;
    }
}
