package com.example.jana.myrecipes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kosara on 12/19/2017.
 */

public class UserRecipesDB extends SQLiteOpenHelper {

    private static final String TAG = RecipeDetailsDB.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyRecipes";
    private static final String USER_RECIPE_TABLE = "UserRecipesDB";

    private SQLiteDatabase mWritableDB;
//    private SQLiteDatabase mReadableDB;

    public static final String RECIPE_ID = "_id";
    public static final String USERNAME = "username";

    private static final String USER_RECIPE_TABLE_CREATE =
            "CREATE TABLE " + USER_RECIPE_TABLE + " (" +
                    RECIPE_ID +  " INTEGER PRIMARY KEY, " +
                    USERNAME +  " );";

    public UserRecipesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct RecipeDetailsDB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_RECIPE_TABLE_CREATE);
    }

    //Insert UserRecipesDB
    public Long InsertUserRecipe (String rec_id,String username) {
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(RECIPE_ID, rec_id);
        values.put(USERNAME,username);
        try {
            if (mWritableDB == null) {mWritableDB = getWritableDatabase();}
            newId = mWritableDB.insert(USER_RECIPE_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;
    }
    //Delete UserRecipesDB
    public int DeleteUserRecipe(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(USER_RECIPE_TABLE, //table name
                    RECIPE_ID + " = ? ", new String[]{String.valueOf(id)});
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
        db.execSQL("DROP TABLE IF EXISTS " + USER_RECIPE_TABLE);
        onCreate(db);

    }
}
