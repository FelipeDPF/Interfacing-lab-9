package com.algonquincollege.depa0028.loginexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText editUser;
    private EditText editPassword;
    private CheckBox editRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.Login);
        editUser = findViewById(R.id.User);
        editPassword = findViewById(R.id.editText);
        editRemember = findViewById(R.id.Remember);

        // register an anonymous inner class as the event handler for the loginButton
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!validateUsername(editUser.getText().toString())) {
                    editUser.requestFocus();
                } else if (!validatePassword(editPassword.getText().toString())) {
                    editPassword.requestFocus();
                } else {
                    // handle the button click: display a Toast message to the user.
                    String display = "User Name: " + editUser.getText() + "\n" + "Password: " + editPassword.getText();
                    Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();

                    // SUCCESS!! All user input has been validated.
                    Toast.makeText(getApplicationContext(), "Email: " + editUser.getText() + " pw: " + editPassword.getText() + " remember? " + editRemember.isChecked(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent( getApplicationContext(), HomeActivity.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    intent.putExtra( "editUser", editUser.getText().toString() );
                    intent.putExtra( "editPassword", editPassword.getText().toString() );
                    intent.putExtra( "editRemember", editRemember.isChecked());
                    startActivity( intent );

                  //  Log.i(LOG_TAG, "LEAVE onClick()");
                }
            }
        });
    }

    protected boolean validateUsername(String username) {
        int i = 4;
        if (username.isEmpty()) {
            editUser.setError("The username is mandatory");
            return false;
        } else if (username.length() != 8) {
            editUser.setError("username must be exactly 8 characters");
            return false;
        } else if ((!Character.isAlphabetic(username.charAt(0)) || Character.isUpperCase(username.charAt(0))) ||
                (!Character.isAlphabetic(username.charAt(1)) || Character.isUpperCase(username.charAt(1)))) {
            editUser.setError("can only be lower-case alphabetic characters (a-z)");
            return false;
        } else if ((!Character.isAlphabetic(username.charAt(2)) && !Character.isDigit(username.charAt(2))) ||
                (!Character.isAlphabetic(username.charAt(3)) && !Character.isDigit(username.charAt(3)))) {
            editUser.setError("can only be lower-case alphabetic characters (a-z) or numbers");
            return false;
        } else {
            while (i < username.length()) {
                if (username.charAt(i) < '0' || username.charAt(i) > '9') {
                    editUser.setError("positions 5 to 8 must be digits (0-9)");
                    return false;
                }
                i++;
            }
            return true;
        }
    }

    protected boolean validatePassword(String password) {
        if (password.isEmpty()) {
            editPassword.setError("The password is mandatory");
            return false;
        }else if(password.length() < 5){
            editPassword.setError("To short (minimum of 5 numbers)");
            return false;
        }else {
            return true;
        }
    }
}