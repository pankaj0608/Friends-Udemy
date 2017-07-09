package com.example.pankaj.friends;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pankaj on 7/9/2017.
 */
public class FriendsDatabase extends SQLiteOpenHelper {

    private static final String TAG = FriendsDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 2;
    private final Context mContext;

    interface Tables {
        String FRIENDS="friends";
    }


    public FriendsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
