package com.algonquincollege.depa0028.loginexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView homeUserEmail;
    private TextView homeUserPassword;
    private TextView homeUserRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeUserEmail = findViewById( R.id.username);
        homeUserPassword = findViewById( R.id.password);
        homeUserRemember = findViewById( R.id.rememberMe);

        // Get the bundle of extras that was sent to this activity
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            String userFromLoginActivity = bundle.getString("editUser");
            String userPasswordFromLoginActivity = bundle.getString("editPassword");
            Boolean userRememberFromLoginActivity = bundle.getBoolean("editRemember");


            homeUserEmail.setText(  "Name: "+  userFromLoginActivity );
            homeUserPassword.setText( "Password "+ userPasswordFromLoginActivity );
            homeUserRemember.setText( "Remember me: "+ userRememberFromLoginActivity.toString() );
        }
    }
}