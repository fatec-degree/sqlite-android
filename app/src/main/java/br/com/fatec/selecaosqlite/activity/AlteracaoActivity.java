package br.com.fatec.selecaosqlite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.fatec.selecaosqlite.R;
import br.com.fatec.selecaosqlite.dao.SelecaoDao;
import br.com.fatec.selecaosqlite.model.Selecao;
import br.com.fatec.selecaosqlite.util.Util;

public class AlteracaoActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextTitulos;
    private Spinner spinnerContinentes;
    private Button buttonAlterar;
    private SelecaoDao selecaoDao = new SelecaoDao(AlteracaoActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao);

        this.editTextNome = findViewById(R.id.editTextAltNome);
        this.editTextTitulos = findViewById(R.id.editTextAltTitulos);
        this.spinnerContinentes = findViewById(R.id.spinnerAltContinente);
        this.buttonAlterar = findViewById(R.id.buttonAlterar);

        Util.carregarContinentes(AlteracaoActivity.this, spinnerContinentes);
        int id = carregarDados();

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Selecao selecao = new Selecao(
                        id,
                        editTextNome.getText().toString(),
                        Integer.parseInt(editTextTitulos.getText().toString()),
                        spinnerContinentes.getSelectedItem().toString());
                int registrosAlterados = selecaoDao.update(selecao);
                if(registrosAlterados > 0){
                    Toast.makeText(AlteracaoActivity.this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AlteracaoActivity.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int carregarDados(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String nome = intent.getStringExtra("nome");
        String titulos = intent.getStringExtra("titulos");
        String continente = intent.getStringExtra("continente");
        int posicao = Util.CONTINENTES.indexOf(continente);

        editTextNome.setText(nome);
        editTextTitulos.setText(titulos);
        spinnerContinentes.setSelection(posicao);

        return id;
    }

}