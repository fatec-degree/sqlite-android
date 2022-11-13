package br.com.fatec.selecaosqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.selecaosqlite.R;
import br.com.fatec.selecaosqlite.dao.SelecaoDao;
import br.com.fatec.selecaosqlite.model.Selecao;
import br.com.fatec.selecaosqlite.util.Util;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFiltroContinente;
    private ListView listViewSelecao;
    private Button buttonNova;

    private SelecaoDao selecaoDao = new SelecaoDao(MainActivity.this);
    private List<Selecao> selecoes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.spinnerFiltroContinente = findViewById(R.id.spinnerFiltroContinente);
        this.listViewSelecao = findViewById(R.id.listViewSelecao);
        this.buttonNova = findViewById(R.id.buttonNova);

        Util.carregarContinentes(MainActivity.this, spinnerFiltroContinente);
        this.selecoes = selecaoDao.selectAll();
        carregarListView(selecoes);

        buttonNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InclusaoActivity.class);
                startActivity(intent);
            }
        });

        spinnerFiltroContinente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String continente = spinnerFiltroContinente.getSelectedItem().toString();
                List<Selecao> selecoes = selecaoDao.selectByContinente(continente);
                carregarListView(selecoes);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listViewSelecao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Deseja excluir este item?");
                alertDialog.setPositiveButton("Sim", (DialogInterface dialogInterface, int i) -> {
                    Selecao selecao = selecoes.get(position);
                    long registrosExcluidos = selecaoDao.delete(selecao.getId());
                    if(registrosExcluidos > 0){
                        Toast.makeText(MainActivity.this, "Excluído", Toast.LENGTH_SHORT).show();
                        carregarListView(selecaoDao.selectAll());
                    } else {
                        Toast.makeText(MainActivity.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("Não", (DialogInterface dialogInterface, int i) -> {});
                alertDialog.show();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarListView(selecaoDao.selectAll());
    }

    private void carregarListView(List<Selecao> selecoes) {
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, selecoes);
        listViewSelecao.setAdapter(adapter);
    }
}