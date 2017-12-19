package com.example.jana.myrecipes;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Jana on 19-Dec-17.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, surnameEditText, emailEditText, email2EditText, usernameEditText, passwordEditText,
                    password2EditText;
    private String userName, userSurname, userEmail, userEmail2, userUsername, userPassword, userPassword2, userGender;
    private RadioGroup genderGroup;
    private RadioButton genderButton;
    private Button registerBtn;
    private boolean isValid = false;
    private boolean valid;

    private Context context;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(R.string.register_details);

        context = RegisterActivity.this;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        nameEditText = (EditText) findViewById(R.id.inputUserName);
        surnameEditText = (EditText) findViewById(R.id.inputUserSurname);
        emailEditText = (EditText) findViewById(R.id.inputUserEmail);
        email2EditText = (EditText) findViewById(R.id.inputUserEmail2);
        usernameEditText = (EditText) findViewById(R.id.input_username);
        passwordEditText = (EditText) findViewById(R.id.inputUserPassword);
        password2EditText = (EditText) findViewById(R.id.inputUserPassword2);
        genderGroup = (RadioGroup) findViewById(R.id.radioGroup);
        registerBtn = (Button) findViewById(R.id.saveUserInfoBtn1);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isValid = validateRegistration();
                SharedPreferences.Editor editor = prefs.edit();
//                if(isValid) {
                    addToDB();
                    editor.putString("isLoggedIn", "y");
                    editor.putString("userUsername", userUsername);
                    editor.commit();
                    Intent intent = new Intent(context, MyProfileActivity.class);
                    startActivity(intent);
//                } else {
//                    Toast.makeText(context, R.string.invalid_registration, Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(context, LoginActivity.class);
//                    startActivity(intent);
//                }
            }
        });
    }

    public boolean validateRegistration(){
        valid = true;

        if (nameEditText.getText().toString().isEmpty() || !nameEditText.getText().toString().matches("[a-zA-Z]+")) {
            nameEditText.setError(getString(R.string.enter_name));
            valid = false;
        } else {
            userName = String.valueOf(nameEditText.getText().toString());
        }

        if (surnameEditText.getText().toString().isEmpty() || !surnameEditText.getText().toString().matches("[a-zA-Z]+")) {
            surnameEditText.setError(getString(R.string.enter_surname));
            valid = false;
        } else {
            userSurname = String.valueOf(surnameEditText.getText().toString());
        }

        if (emailEditText.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
            emailEditText.setError(getString(R.string.enter_email));
            valid = false;
        } else {
            userEmail = String.valueOf(emailEditText.getText().toString());
        }

        if (email2EditText.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email2EditText.getText().toString()).matches()) {
            email2EditText.setError(getString(R.string.enter_email));
            valid = false;
        } else {
            userEmail2 = String.valueOf(email2EditText.getText().toString());
            if (!userEmail.equals(userEmail2)) {
                Toast.makeText(getApplicationContext(), R.string.email_not_matching, Toast.LENGTH_LONG).show();
                email2EditText.setError(getString(R.string.enter_email));
                valid = false;
            }
        }

        if (usernameEditText.getText().toString().isEmpty() || !checkIfUserExist((usernameEditText.getText().toString()))) {
            usernameEditText.setError(getString(R.string.enter_username));
            valid = false;
        } else {
            userUsername = String.valueOf(usernameEditText.getText().toString());
        }

        if(passwordEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().length() < 6)
        {
            passwordEditText.setError(getString(R.string.password_criteria));
            valid = false;
        } else {
            userPassword = String.valueOf(passwordEditText.getText().toString());
        }
        if (password2EditText.getText().toString().isEmpty() || password2EditText.getText().toString().length() < 6) {
            password2EditText.setError(getString(R.string.password_criteria));
            valid = false;
        } else {
            userPassword2 = String.valueOf(password2EditText.getText().toString());
            if (!userPassword.equals(userPassword2)) {
                Toast.makeText(getApplicationContext(), R.string.passwords_not_matching, Toast.LENGTH_LONG).show();
                password2EditText.setError(getString(R.string.enter_password));
                valid = false;
            }
        }
        int selectedId = genderGroup.getCheckedRadioButtonId();
        genderButton = (RadioButton) findViewById(selectedId);
        userGender = genderButton.getText().toString();

        if(valid) return true;
        else return false;
    }

    public boolean checkIfUserExist(String username){
        return false;
    }

    public void addToDB(){

    }
}
