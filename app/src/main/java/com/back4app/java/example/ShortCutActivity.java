package com.back4app.java.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShortCutActivity extends AppCompatActivity {

    TextView olaTextView;
    Button buttonAmostrar;
    Button buttonFuncionarios;
    Button buttonMinhasAmostras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut);

        olaTextView = (TextView) findViewById(R.id.olaTextView);
        buttonMinhasAmostras = (Button) findViewById(R.id.buttonMinhasAmostras);
        buttonMinhasAmostras.setOnClickListener(
                view -> {
                    showSampleListActivity();
                }
        );
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
    //Amostrar
    public void showSamplingActivity() {
        Toast.makeText(this, "Indo para Amostrar",
                Toast.LENGTH_SHORT).show();

    }
    //Funcionarios
    public void showEmployeesActivity() {
        Toast.makeText(this, "Indo para Funcionarios",
                Toast.LENGTH_SHORT).show();

    }
    //Minhas Amostras
    public void showSampleListActivity(){
        Toast.makeText(this, "Indo para Minhas Amostras",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), SampleListActivity.class);
        startActivity(intent);
    }

    public void showHome(){
        Toast.makeText(this, "Indo para Home",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}