package com.example.jana.myrecipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Jana on 19-Dec-17.
 */

public class MyProfileActivity extends AppCompatActivity {

    private ImageView imgLogout;
    private SharedPreferences prefs;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle(R.string.my_profile);

        context = MyProfileActivity.this;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        imgLogout = (ImageView) findViewById(R.id.imgLogout);
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("isLoggedIn", "n");
                editor.putString("userUsername", "");
                editor.commit();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
