package com.back4app.java.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SampleListActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames;
    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);
        // data to populate the RecyclerView with
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Samples");

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(
                view -> {
                    Toast.makeText(this, "You clicked on Add ", Toast.LENGTH_SHORT).show();
                    showSampleInsertActivity();
                }
        );

        animalNames = new ArrayList<>();
        query.whereExists("Product");
        query.addAscendingOrder("Product");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.sampleListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject obj : objects) {
                            String myvalue;
                            /*TODO Produto vem, pq não esta mostrando;*/
                            myvalue = obj.getString("Product");
                            //Log.d("myValue", "myValue: " + myvalue);
                            animalNames.add(myvalue);
                        }
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

        /*ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");*/

        // set up the RecyclerView



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
                    showHome();
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
    public void showHome(){
        Toast.makeText(this, "Indo para Home",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
    //SampleInsertActivity
    public void showSampleInsertActivity(){
        Toast.makeText(this, "Indo para Insere Amostra",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), SampleInsertActivity.class);
        startActivity(intent);
    }
}