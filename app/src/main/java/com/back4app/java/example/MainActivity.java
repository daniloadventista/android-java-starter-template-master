package com.back4app.java.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.ParseAnalytics;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* inserted from the course */
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        TextView textView = findViewById(R.id.textView);
        ParseObject firstObject = new  ParseObject("FirstClass");
        firstObject.put("message","Hey ! First message from android. Parse is now connected");

        firstObject.saveInBackground(e -> {
            if (e != null){
                Log.e("MainActivity", e.getLocalizedMessage());
            }else{
                Log.d("MainActivity","Object saved.");
                textView.setText(String.format("Object saved. %s", firstObject.getObjectId()));
            }
        });

        /* query with object id  2CjaznUgWh*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("FirstClass");
        query.whereEqualTo("message", "Test");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> firstClassList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + firstClassList.size() + " FirstClass");
                    for (ParseObject o: firstClassList
                         ) {
                        int myValue = o.getInt("UpdatedValue");
                        o.put("UpdatedValue",myValue+1);
                        o.saveInBackground();
                        Log.d("UpdatedValue", "myValue: " + myValue+1);
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });



    }
}