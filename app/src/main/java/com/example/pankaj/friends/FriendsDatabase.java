package com.example.pankaj.friends;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by pankaj on 7/9/2017.
 */
public class FriendsDatabase extends SQLiteOpenHelper {

    private static final String TAG = FriendsDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 2;
    private final Context mContext;

    interface Tables {
        String FRIENDS = "friends";
    }


    public FriendsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Tables.FRIENDS + " ("
                + BaseColumns._ID + " integer primary key  autoi'ncrement"
                + FriendsContract.FriendsColumns.FRIENDS_NAME + " text not null"
                + FriendsContract.FriendsColumns.FRIENDS_EMAIL + " text not null"
                + FriendsContract.FriendsColumns.FRIENDS_PHONE + " text not null");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int oldVersion, int newVersion) {

        int version = oldVersion;
        if(version == 1) {
            //Add some extra fields to the database without deleting existing data
            version = 2;
        }

        if(version != DATABASE_VERSION) {
            sqLiteDatabase.execSQL("drop table if exists " + Tables.FRIENDS);
            onCreate(sqLiteDatabase);
        }
    }

    public static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
