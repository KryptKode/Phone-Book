package com.kryptkode.cyberman.phonebook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cyberman on 5/14/2017.
 */

public class PhoneBookDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PhoneBook.db";
    private static final int DATABASE_VERSION = 1;

    public PhoneBookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_PEOPLE_TABLE =
                "CREATE TABLE " + DatabaseDescription.People.TABLE_NAME + "("+
                        DatabaseDescription.People._ID + " integer primary key, " +
                        DatabaseDescription.People.COLUMN_NAME + " TEXT, " +
                        DatabaseDescription.People.COLUMN_NICKNAME + " TEXT, " +
                        DatabaseDescription.People.COLUMN_PHONE + " TEXT, " +
                        DatabaseDescription.People.COLUMN_ADDRESS + " TEXT, " +
                        DatabaseDescription.People.COLUMN_CITY + " TEXT, " +
                        DatabaseDescription.People.COLUMN_STATE + " TEXT, " +
                        DatabaseDescription.People.COLUMN_COMPANY + " TEXT, " +
                        DatabaseDescription.People.COLUMN_WEBSITE + " TEXT, " +
                        DatabaseDescription.People.COLUMN_ZIPCODE + " TEXT);";
        db.execSQL(CREATE_PEOPLE_TABLE); //creates the data base table

    }

    //defines how to upgrade the database when changes in the version occurs, we do not need it for now
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
