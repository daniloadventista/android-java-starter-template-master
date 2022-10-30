package com.back4app.java.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SampleInsertActivity extends AppCompatActivity {

    InstantAutoComplete actvGrupo;
    InstantAutoComplete actvProduto;
    InstantAutoComplete actvAtividade;
    // TODO Alimentar Grupos da Web
    String [] grupos = new String[] {"Crédito", "Renegociação", "Investimento"};
    String [] produtos = new String[] {"Crediário", "Consignado", "Sobmedida", "Fundos"};
    String [] atividades = new String[] {"Oferta", "Simulação", "Efetivação", "Resgate"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_insert);
        actvGrupo = (InstantAutoComplete) findViewById(R.id.actvGrupo);
        actvGrupo.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, grupos));
        actvProduto = (InstantAutoComplete) findViewById(R.id.actvProduto);
        actvProduto.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, produtos));
        actvAtividade = (InstantAutoComplete) findViewById(R.id.actvAtividade);
        actvAtividade.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, atividades));
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
}