package com.back4app.java.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean changeSignpModeActive = true;

    TextView changeSignpModeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeSignpModeTextView = (TextView) findViewById(R.id.changeSignpModeTextView);
        changeSignpModeTextView.setOnClickListener(this);

    }

    public void signUp(View view){

        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);

        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        if (usernameEditText.getText().toString().matches("")
        || passwordEditText.getText().toString().matches("")){
            Toast.makeText(this, "Preecher nome e password.",
                    Toast.LENGTH_SHORT).show();
        }else{

            if(changeSignpModeActive) {

                ParseUser user = new ParseUser();
                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("Sign Up", "Successful");
                        } else {
                            Log.d("Sign Up", "Error: " + e.getMessage());
                        }
                    }
                });
            } else {

                ParseUser.logInInBackground(usernameEditText.getText().toString()
                        , passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null){
                            Log.d("Login", "Successfull");
                        }else{
                            Log.d("Login", "Failed " + e.toString());
                        }

                    }
                });

            }

        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changeSignpModeTextView){

            Log.d("App Info", "Change Sign Up Mode");

            Button signupButton = (Button) findViewById(R.id.signupButton);

            if(changeSignpModeActive){

                changeSignpModeActive = false;
                signupButton.setText("Login");
                changeSignpModeTextView.setText("Or, Sign Up");

            }else{

                changeSignpModeActive = true;
                signupButton.setText("Sign Up");
                changeSignpModeTextView.setText("Or, Login");

            }

        }
    }
    /*
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }*/

}