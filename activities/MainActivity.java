package com.example.jana.myrecipes.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jana.myrecipes.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if(prefs.getString("isLoggedIn", null) != null && prefs.getString("isLoggedIn", null).equals("y")) {
            Intent intent = new Intent(context, MyProfileActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }

    }
}

