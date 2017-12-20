package com.example.jana.myrecipes.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jana.myrecipes.R;
import com.example.jana.myrecipes.db.UserDetailsDB;
import com.example.jana.myrecipes.models.UserModel;

import org.w3c.dom.Text;

/**
 * Created by Jana on 19-Dec-17.
 */

public class MyProfileActivity extends AppCompatActivity {

    private ImageView imgLogout;
    private ImageView imgEdit;
    private SharedPreferences prefs;
    private Context context;
    private Button myRecipesButton;
    private UserDetailsDB dbUserDet;
    private TextView userUsernameText;
    private TextView userNameSurnameText;
    private TextView userEmailText;
    private TextView userEmailText2;
    private UserModel userModelDetails;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle(R.string.my_profile);

        context = MyProfileActivity.this;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        userUsernameText = (TextView)findViewById(R.id.userNameText);
        userNameSurnameText = (TextView) findViewById(R.id.userNameSurname);
        userEmailText = (TextView) findViewById(R.id.userEmailText);
        userEmailText2 = (TextView)findViewById(R.id.userEmailText2);

        myRecipesButton = (Button) findViewById(R.id.myRecipesBtn);
        imgLogout = (ImageView) findViewById(R.id.imgLogout);
        imgEdit = (ImageView) findViewById(R.id.editUserAccount);

        dbUserDet = new UserDetailsDB(this);
        username = prefs.getString("userUsername", null);

        fillMyProfile();

        myRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyRecipesActivity.class);
                startActivity(intent);
            }
        });

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

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void fillMyProfile(){
        userModelDetails = dbUserDet.showUserDetails(username);
        userUsernameText.setText(userModelDetails.getUSERNAME());
        userNameSurnameText.setText(userModelDetails.getNAME() + " " + userModelDetails.getSURNAME());
        userEmailText.setText(userModelDetails.getEMAIL());
        userEmailText2.setText(userModelDetails.getEMAIL());
    }
}
