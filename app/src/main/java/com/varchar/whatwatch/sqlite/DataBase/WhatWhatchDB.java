package com.varchar.whatwatch.sqlite.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.varchar.whatwatch.model.VideoMedia;
import com.varchar.whatwatch.sqlite.contrat.VideoMediaEntry;

import java.util.ArrayList;
import java.util.List;

public class WhatWhatchDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "WhatWatch.db";

    private  static  final String sqlCreate = "CREATE TABLE " + VideoMediaEntry.TABLE_NAME + " ("
            + VideoMediaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VideoMediaEntry.ID + " INTEGER,"
            + VideoMediaEntry.NAME + " TEXT NOT NULL,"
            + VideoMediaEntry.LOCAL_IMAGE_ID + " INTEGER NOT NULL,"
            + "UNIQUE (" + VideoMediaEntry.NAME + "))";

    private static WhatWhatchDB dbInstance;

    private WhatWhatchDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static WhatWhatchDB getInstance(Context context){
        if(dbInstance == null){
            dbInstance = new WhatWhatchDB(context);
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
                new String[]{VideoMediaEntry.NAME, VideoMediaEntry.LOCAL_IMAGE_ID },
                null,
                null,
                null,
                null,
                null
        );

        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex(VideoMediaEntry.NAME));
            int localResourceId = c.getInt(c.getColumnIndex(VideoMediaEntry.LOCAL_IMAGE_ID));
            favourites.add(VideoMedia.fromLocalResources(localResourceId, name));
        }
        return favourites;
    }
}
