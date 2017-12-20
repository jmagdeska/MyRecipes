package com.example.jana.myrecipes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jana.myrecipes.models.UserModel;

/**
 * Created by Kosara on 12/19/2017.
 */

public class UserDetailsDB extends SQLiteOpenHelper {

    private static final String TAG = UserDetailsDB.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyRecipes";
    private static final String USER_TABLE = "UserDetailsDB";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

//    Username , Password,  e-mail, Name, Surname
    public static final String ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    //

    private static final String USER_DETAILS_TABLE_CREATE =
            "CREATE TABLE " + USER_TABLE + " (" +
                    ID +  " INTEGER PRIMARY KEY, " +
                    USERNAME + ", " +
                    PASSWORD + ", " +
                    EMAIL + ", " +
                    NAME + ", " +
                    SURNAME  + " );";


    public UserDetailsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct UserDetailsDB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_DETAILS_TABLE_CREATE);
    }

    public boolean checkUser(String username){
        String select_query  = "SELECT * FROM " + USER_TABLE +" WHERE username = '"+username+"'";

        Cursor cursor = null;

        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        cursor = mReadableDB.rawQuery(select_query, null);

        if(cursor.getCount() <= 0) return false;
        else return true;
    }

    public boolean validateLogin(String username, String password){
        String select_query  = "SELECT * FROM " + USER_TABLE +" WHERE username = '"+username+"'";

        Cursor cursor = null;
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        cursor = mReadableDB.rawQuery(select_query, null);

        String userPass = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userPass = cursor.getString(cursor.getColumnIndex(PASSWORD));
            }
            cursor.close();
        }
        System.out.print("lozinki " + userPass + password);

        if(userPass.equals(password)) return true;
        else return false;
    }

    //insert user
    public Long insertUser(String username, String password, String email, String name, String surname) {
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD,password);
        values.put(EMAIL,email);
        values.put(NAME,name);
        values.put(SURNAME,surname);
        try {
            if (mWritableDB == null) {mWritableDB = getWritableDatabase();}
            newId = mWritableDB.insert(USER_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;
    }

    //update user
    public int updateUser(String username, String password, String email) {
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {mWritableDB = getWritableDatabase();}
            ContentValues values = new ContentValues();
            values.put(PASSWORD, password);
            values.put(EMAIL,email);

            mNumberOfRowsUpdated = mWritableDB.update(USER_TABLE, //table to change
                    values, // new values to insert
                    USERNAME + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(username)}); //selection args; the actual value of the id

        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }

    public UserModel showUserDetails(String username) {
        String select_query = "SELECT * FROM " + USER_TABLE + " WHERE username = '" + username + "'";
        Cursor cursor = null;
        UserModel entry = new UserModel();

        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        cursor = mReadableDB.rawQuery(select_query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                entry.setID(cursor.getColumnIndex(ID));
                entry.setUSERNAME(cursor.getString(cursor.getColumnIndex(USERNAME)));
                entry.setNAME(cursor.getString(cursor.getColumnIndex(NAME)));
                entry.setSURNAME(cursor.getString(cursor.getColumnIndex(SURNAME)));
                entry.setEMAIL(cursor.getString(cursor.getColumnIndex(EMAIL)));
            }
            cursor.close();
        }
        return entry;
    }

    //delete user
    public int deleteUser(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(USER_TABLE, //table name
                    ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d(TAG, "DELETE EXCEPTION! " + e.getMessage());
        }
        return deleted;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(UserDetailsDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}
