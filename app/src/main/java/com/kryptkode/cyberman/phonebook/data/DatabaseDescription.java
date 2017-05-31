package com.kryptkode.cyberman.phonebook.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by Cyberman on 5/14/2017.
 */

public class DatabaseDescription {
    //content provider name
    public static final  String AUTHORITY =
            "com.kryptkode.cyberman.phonebook.data";

    //base URI used to communicate with the Content Provider
    private static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);


    public static final class People implements BaseColumns{
        public static final String TABLE_NAME = "people"; // the name of the table

        public static final Uri URI_CONTENT =
                BASE_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NICKNAME = "nickname";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_COMPANY = "company";
        public static final String COLUMN_WEBSITE = "website";
        public static final String COLUMN_ZIPCODE = "zipcode";


        public static Uri buildPeopleUri (long id){
            return ContentUris.withAppendedId(URI_CONTENT, id);
        }

    }

}
