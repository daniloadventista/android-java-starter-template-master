package com.back4app.java.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean changeSignpModeActive = true;

    TextView changeSignpModeTextView;

    EditText usernameEditText;

    EditText passwordEditText;

    ConstraintLayout  backgroundRelativeLayout;

    ImageView logoImageView;


    public void showCutActivity() {

        Intent intent = new Intent(getApplicationContext(), ShortCutActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

            signUp(v);

        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeSignpModeTextView = (TextView) findViewById(R.id.changeSignpModeTextView);
        changeSignpModeTextView.setOnClickListener(this);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setOnKeyListener(this);
        backgroundRelativeLayout = (ConstraintLayout) findViewById(R.id.backgroundRelativeLayout);
        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        backgroundRelativeLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            showCutActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.get_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, "You clicked on Home "
                        , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.back:
                Toast.makeText(this, "You clicked on Back "
                        , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.next:
                Toast.makeText(this, "You clicked on Next "
                        , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subItem1:
                Toast.makeText(this, "You clicked on SubItem1 "
                        , Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void signUp(View view){

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
                            showCutActivity();
                        } else {
                            Log.d("Sign Up", "Error: " + e.getMessage());
                        }
                    }
                });
                Toast.makeText(this, "Sign Up, Successful",
                        Toast.LENGTH_SHORT).show();
            } else {

                ParseUser.logInInBackground(usernameEditText.getText().toString()
                        , passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null){
                            Log.d("Login", "Successfull");
                            showCutActivity();
                        }else{
                            Log.d("Login", "Failed " + e.toString());
                        }
                    }
                });
                Toast.makeText(this, "Login, Successfull",
                        Toast.LENGTH_SHORT).show();

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

        } else if (view.getId() == R.id.backgroundRelativeLayout || view.getId() == R.id.logoImageView) {

            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(
                            INPUT_METHOD_SERVICE);
            if(inputMethodManager.isAcceptingText()) {
                inputMethodManager.hideSoftInputFromWindow(
                        getCurrentFocus().getWindowToken(),
                        0
                );
            }
        }
    }

}