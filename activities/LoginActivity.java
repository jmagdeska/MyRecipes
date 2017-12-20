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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jana.myrecipes.R;
import com.example.jana.myrecipes.db.UserDetailsDB;

/**
 * Created by Jana on 19-Dec-17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences prefs;
    private Context context;
    private Button loginBtn;
    private TextView registerBtn;
    private UserDetailsDB dbUserDet;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        loginBtn = (Button) findViewById(R.id.btn_login1);
        registerBtn = (TextView) findViewById(R.id.btn_register1);
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        dbUserDet = new UserDetailsDB(this);
    }

    @Override
    public void onClick(View v) {
        if(v == loginBtn) {
            boolean valid = validateLogin(username.getText().toString(), password.getText().toString());
            if(valid) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("userUsername", username.getText().toString());
                editor.putString("isLoggedIn", "y");
                editor.commit();
                Intent intent = new Intent(context, MyProfileActivity.class);
                startActivity(intent);
            }
            else Toast.makeText(context, "Invalid login! Try again.", Toast.LENGTH_LONG).show();
        }
        else if (v == registerBtn) {
            Intent intent = new Intent(context, RegisterActivity.class);
            startActivity(intent);
        }
    }

    public boolean validateLogin(String username, String password) {
        //connect with insert UserDetailsDB SelectUser
        return dbUserDet.validateLogin(username, password);
    }
}
