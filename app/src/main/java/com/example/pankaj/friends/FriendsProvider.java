package com.example.pankaj.friends;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by pankaj on 7/9/2017.
 */
public class FriendsProvider extends ContentProvider {

    private FriendsDatabase friendsDatabase;

    private static String TAG = FriendsProvider.class.getSimpleName();
    private static UriMatcher uriMatcher = buildUriMatcher();

    private static final int FRIENDS = 100;
    private static final int FRIENDS_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FriendsContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, "friends", FRIENDS);
        matcher.addURI(authority, "friends/*", FRIENDS_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        friendsDatabase = new FriendsDatabase(getContext());
        return true;
    }


    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case FRIENDS:
                return FriendsContract.Friends.CONTENT_TYPE;

            case FRIENDS_ID:
                return FriendsContract.Friends.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = friendsDatabase.getReadableDatabase();
        final int match = uriMatcher.match(uri);

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(FriendsDatabase.Tables.FRIENDS);

        switch (match) {
            case FRIENDS:
                break;

            case FRIENDS_ID:
                String id=FriendsContract.Friends.getFriendId(uri);
                sqLiteQueryBuilder.appendWhere(BaseColumns._ID + "=" + id);
                break;

            default:
                break;
        }

        return sqLiteQueryBuilder.query(
                friendsDatabase.getReadableDatabase(), projection,
                selection,selectionArgs,
                null,null,sortOrder);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    private void deleteDatabase() {
        friendsDatabase.close();
        ;
        FriendsDatabase.deleteDatabase(getContext());
        friendsDatabase = new FriendsDatabase(getContext());
    }
}
