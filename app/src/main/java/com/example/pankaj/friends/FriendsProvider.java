package com.example.pankaj.friends;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

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
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
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
        friendsDatabase.close();;
        FriendsDatabase.deleteDatabase(getContext());
        friendsDatabase = new FriendsDatabase(getContext());
    }
}
