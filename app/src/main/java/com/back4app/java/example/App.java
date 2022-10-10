package com.back4app.java.example;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

import java.time.LocalDateTime;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server("http://ec2-52-67-121-91.sa-east-1.compute.amazonaws.com:80/parse")
                .build());


        saveNewLogin(this);

    }
    private void saveNewLogin(Context context) {
        ParseObject newLogin = new ParseObject("NewLogin");
        String email = "";
        String myEmail = UserEmailFetcher.getEmail(context);
        if (myEmail != null) {
            email = myEmail;
        }
        newLogin.put("emailContact", email);
        newLogin.saveInBackground();
    }
}
