package com.kryptkode.cyberman.phonebook.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.kryptkode.cyberman.phonebook.R;

public class PhoneBookContentProvider extends ContentProvider {

    private  PhoneBookDatabaseHelper helper;  //used to access the database

    //UriMatcher helps ContentProvider determine operation to perform
    private static  final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //used with the URiMatcher to determine the task to perform
    private static final int PERSON = 1; //handle one contact
    private static  final int PEOPLE = 11; //handle the whole table

    public PhoneBookContentProvider() {
    }


    static {

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.People.TABLE_NAME + "/#",PERSON);


        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.People.TABLE_NAME, PEOPLE);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int numberOfRowsUpdated;

        switch (uriMatcher.match(uri)){

            case PERSON:
                //get the iD from the URi
                String id = uri.getLastPathSegment();

                //delete the person from the table
                numberOfRowsUpdated = helper.getWritableDatabase().delete(
                        DatabaseDescription.People.TABLE_NAME, DatabaseDescription.People._ID +
                                "=" + id, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(getContext().
                        getString(R.string.incorrect_delete_uri) + uri);

        }

        //if delete was made, notify the resolver
        if (numberOfRowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numberOfRowsUpdated;
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       Uri newUri  = null;

        switch (uriMatcher.match(uri)){

            case PEOPLE:
                long rowID = helper.getWritableDatabase().insert(
                        DatabaseDescription.People.TABLE_NAME, null, values);

                if (rowID > 0){
                    newUri = DatabaseDescription.People.buildPeopleUri(rowID);

                    getContext().getContentResolver().notifyChange(uri, null);
                }
                else{
                    throw new SQLException(getContext().getString(R.string.insert_failure) + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException(
                        getContext().getString(R.string.invalid_insert_uri) + uri);
        }

        return newUri;
    }


    //called when the Content Provider is created
    @Override
    public boolean onCreate() {
        //instantiate the helper
        helper = new PhoneBookDatabaseHelper(getContext());
        return true; //true, cos the content provider has been successfully created
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DatabaseDescription.People.TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case PERSON: //select the contact with the specified ID
                queryBuilder.appendWhere(
                        DatabaseDescription.People._ID + "=" + uri.getLastPathSegment());
                break;
            case PEOPLE: //select all the contacts
                break;
            default:
                throw new UnsupportedOperationException(
                        getContext().getString(R.string.invalid_query_uri));

        }

        Cursor cursor = queryBuilder.query(helper.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int numberOfRowsUpdated;

        switch (uriMatcher.match(uri)){

            case PERSON:
                //get the iD from the URi
                String id = uri.getLastPathSegment();

                //update the table
                numberOfRowsUpdated = helper.getWritableDatabase().update(
                        DatabaseDescription.People.TABLE_NAME, values, DatabaseDescription.People._ID +
                                "=" + id, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(getContext().getString(R.string.incorrect_update_uri) + uri);

        }

        //if update was made, notify the resolver
        if (numberOfRowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
return numberOfRowsUpdated;
    }
}
