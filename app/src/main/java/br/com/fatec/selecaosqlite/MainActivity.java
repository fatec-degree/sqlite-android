package br.com.fatec.selecaosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.fatec.selecaosqlite.dao.SelecaoDao;
import br.com.fatec.selecaosqlite.model.Selecao;

public class MainActivity extends AppCompatActivity {

    private ListView listViewSelecao;
    private Button buttonNova;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        this.listViewSelecao = findViewById(R.id.listViewSelecao);
        this.buttonNova = findViewById(R.id.buttonNova);

        carregarListView();

        buttonNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InclusaoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarListView();
    }

    private void carregarListView() {
        SelecaoDao selecaoDao = new SelecaoDao(MainActivity.this);
        ArrayList<Selecao> selecoes = selecaoDao.selectAll();
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, selecoes);
        listViewSelecao.setAdapter(adapter);
    }
}