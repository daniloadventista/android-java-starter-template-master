package com.back4app.java.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.ParseAnalytics;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser.logOut();

        if (ParseUser.getCurrentUser() != null){
            Log.d("currentUser", "User Logged In " + ParseUser.getCurrentUser().getUsername());
        }else{
            Log.d("currentUser", "User Not Logged In ");
        }


        /*
        ParseUser.logInInBackground("Danilo", "asdasd", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null){
                    Log.d("Login", "Successfull");
                }else{
                    Log.d("Login", "Failed " + e.toString());
                }

            }
        });
        */

        /*
        ParseUser user = new ParseUser();
        user.setUsername("Danilo");
        user.setPassword("myPass");
        user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("Sign Up", "Successfull");
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
        ;

        ;
        */


    }
}