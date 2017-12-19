package com.example.jana.myrecipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jana on 19-Dec-17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private Button loginBtn;
    private TextView registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;

        loginBtn = (Button) findViewById(R.id.btn_login1);
        registerBtn = (TextView) findViewById(R.id.btn_register1);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == loginBtn) {
            validateLogin();
        }
        else if (v == registerBtn) {
            Intent intent = new Intent(context, RegisterActivity.class);
            startActivity(intent);
        }
    }

    public void validateLogin() {

    }
}
